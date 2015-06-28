package com.cqupt.mis.rms.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.ProofDao;
import com.cqupt.mis.rms.dao.ResearchDataDao;
import com.cqupt.mis.rms.dao.ResearchFiledDao;
import com.cqupt.mis.rms.dao.ResearchPersonDao;
import com.cqupt.mis.rms.dao.ResearchRecordDao;
import com.cqupt.mis.rms.model.Proof;
import com.cqupt.mis.rms.model.ResearchData;
import com.cqupt.mis.rms.model.ResearchFiled;
import com.cqupt.mis.rms.model.ResearchPerson;
import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.service.ResearchRecordService;

/**
 * 处理科研记录的逻辑层实现
 * @author Bern
 *
 */
@Service
public class ResearchRecordServiceImpl implements ResearchRecordService {
	@Resource
	private ResearchRecordDao researchRecordDao;	//科研记录类的Dao
	@Resource
	private ResearchDataDao researchDataDao;		//科研数据类Dao
	@Resource
	private ResearchPersonDao researchPersonDao;		//科研相关人员类的Dao
	@Resource
	private ProofDao proofDao;		//旁证材料类的Dao
	@Resource
	private ResearchFiledDao researchFiledDao;		//	科研字段类的Dao
	
	public boolean add(ResearchRecord record) {
		boolean result1 = researchRecordDao.add(record);
		boolean result2 = researchDataDao.addSet(record.getFields(), record.getId());
		boolean result3 = researchPersonDao.addList(record.getPersons(), record.getId());
		boolean result4 = proofDao.addList(record.getProofs(), record.getId());
		
		//TODO 存储旁证材料文件
		
		if(result1 && result2 && result3 && result4)
			return true;
		
		return false;
	}

	public ResearchRecord findOneById(String recordId) {
		ResearchRecord record;
		
		record = researchRecordDao.selectByPrimaryKey(recordId);
		
		if(record == null) {
			return null;
		}
		
		List<Proof> proofs = proofDao.findListByRecordId(recordId);
		List<ResearchPerson> persons = researchPersonDao.findListByRecordId(recordId);
		Set<ResearchData> datas = researchDataDao.findList1(recordId, record.getResearchClass().getClassId());
		
		//拼装完整科研记录信息
		record.setFields(datas);
		record.setPersons(persons);
		record.setProofs(proofs);
		
		return record;
	}

	public boolean deleteById(String recordId) {
		//TODO	删除旁证材料的文件
		//TODO	验证旁证材料的状态？
		
		return researchRecordDao.deleteByPrimaryKey(recordId);
	}

	public boolean modify(ResearchRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean accept(ResearchRecord record, String approvedUserId) {
		return researchRecordDao.accept(record, approvedUserId);
	}

	public boolean refuse(ResearchRecord record, String approvedUserId) {
		return researchRecordDao.refuse(record, approvedUserId);
	}

	public List<ResearchRecord> findListByUserAndClass(String userId, int classId) {
		//获取符合条件的科研记录
		List<ResearchRecord> records = researchRecordDao.findListByUserAndClass(userId, classId);
		return findListByRecords(records);
	}

	public List<ResearchRecord> findListByClassForApprove(int classId) {
		//TODO 权限验证
		List<ResearchRecord> records = researchRecordDao.findListByClassForApprove(classId);
		return findListByRecords(records);
	}
	
	/**
	 * 获取一组科研记录的完整信息（包括数据信息，旁证材料，科研记录相关人）
	 * @param records
	 * @return 
	 */
	public List<ResearchRecord> findListByRecords(List<ResearchRecord> records) {
		List<String> recordIds;
		int classId;
		
		if(records.size() == 0) {
			return records;
		} else {
			classId = records.get(0).getResearchClass().getClassId();
			recordIds = new ArrayList<String>();
		}
		
		for(ResearchRecord r : records) {
			recordIds.add(r.getId());
		}
		//获取符合条件的各种科研信息
		List<ResearchData> datas = researchDataDao.findList2(recordIds, classId);
		List<ResearchPerson> persons = researchPersonDao.findListByRecordIds(recordIds);
		List<Proof> proofs = proofDao.findListByRecordIds(recordIds);
		//获取相应类别下的所有字段
		List<ResearchFiled> fileds = researchFiledDao.findByClassId(classId);
		
		//按recordId对科研各种信息进行分类
		Map<String, List<ResearchData>> dataMap = convertMap(datas);
		Map<String, List<ResearchPerson>> personMap = convertMap(persons);
		Map<String, List<Proof>> proofMap = convertMap(proofs);
		
		//拼装科研信息
		for(ResearchRecord r : records) {
			String recordId = r.getId();
			Set<ResearchData> dataSet = new HashSet<ResearchData>(dataMap.get(recordId));
			//将相应字段为空的数据加入Set集合
			for(ResearchFiled f : fileds) {
				ResearchData data = new ResearchData();
				data.setFiled(f);
				dataSet.add(data);
			}
			
			r.setFields(dataSet);
			r.setPersons(personMap.get(recordId));
			r.setProofs(proofMap.get(recordId));
		}
		
		return records;
	}
	
	/**
	 * 对list中的数据根据“recordId”进行分类，其中V类型中必须有字段名为“recordId”
	 * @param list
	 * @return 分类结果
	 */
	public <V> Map<String, List<V>> convertMap(List<V> list) {
		Map<String, List<V>> map;
		if(list.size() == 0) {
			return null;
		}
		//获取V的类型和V中名为“recordId”的字段
		Class<?> class1 = list.get(0).getClass();
		Field field;
		try {
			field = class1.getDeclaredField("recordId");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		}
		field.setAccessible(true);
		
		map = new HashMap<String, List<V>>();
		
		//进行分类
		for(V item : list) {
			String recordId;
			try {
				recordId = (String) field.get(item);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
			if(map.containsKey(recordId)) {
				map.get(recordId).add(item);
			} else {
				List<V> vs = new ArrayList<V>();
				vs.add(item);
				map.put(recordId, vs);
			}
		}
		
		return map;
	}


}
