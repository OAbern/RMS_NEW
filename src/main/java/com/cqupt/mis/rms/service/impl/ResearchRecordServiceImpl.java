package com.cqupt.mis.rms.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import javax.annotation.Resource;

import com.cqupt.mis.rms.utils.GenerateUtils;
import com.cqupt.mis.rms.utils.ResearchConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.ProofDao;
import com.cqupt.mis.rms.dao.ResearchDataDao;
import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.dao.ResearchPersonDao;
import com.cqupt.mis.rms.dao.ResearchRecordDao;
import com.cqupt.mis.rms.model.Proof;
import com.cqupt.mis.rms.model.ResearchData;
import com.cqupt.mis.rms.model.ResearchField;
import com.cqupt.mis.rms.model.ResearchPerson;
import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.service.ResearchRecordService;
import org.springframework.util.CollectionUtils;

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
	private ResearchFieldDao researchFieldDao;		//	科研字段类的Dao
	
	public ResultInfo<Object> add(ResearchRecord record, List<FileItem> proofFiles) {
		//TODO： 失败回滚操作


		if(CollectionUtils.isEmpty(record.getFields()))
			return new ResultInfo<Object>(false, "添加科研记录异常，没有字段数据！");

		boolean result = researchRecordDao.add(record);
		if(!result)
			return new ResultInfo<Object>(false, "添加主记录失败！");

		result = researchDataDao.addSet(record.getFields(), record.getId());
		if(!result)
			return new ResultInfo<Object>(false, "添加动态字段数据失败！");

		if(!CollectionUtils.isEmpty(record.getPersons())) {		//仅当人员信息非空时进行插入，否则略过
			result = researchPersonDao.addList(record.getPersons(), record.getId());
			if(!result)
				return new ResultInfo<Object>(false, "添加相关人员信息失败！");
		}

		if(!CollectionUtils.isEmpty(proofFiles)) {
			//存储文件
			List<Proof> proofList = parseAndStoreProofs(proofFiles);
			result = proofDao.addList(proofList, record.getId());
			if(!result)
				return new ResultInfo<Object>(false, "添加旁证材料失败！");
		}
		
		return new ResultInfo<Object>(null, true);
	}

	/**
	 * 解析并且保存旁证材料的文件
	 * @param proofFiles List<FileItem> form-data中获取的源数据
	 * @return	List<Proof> 旁证材料的数据信息
     */
	private List<Proof> parseAndStoreProofs(List<FileItem> proofFiles) {
		//检查根目录文件夹是否存在
		File dir = new File(ResearchConstant.PROOF_STORE_PATH_ROOT);
		if(!dir.exists()) {
			dir.mkdir();
		}

		List<Proof> proofList = new ArrayList<Proof>();
		for(FileItem fileItem : proofFiles) {
			Proof proof = new Proof();
			String originalName = fileItem.getName();
			String genName = GenerateUtils.generateFileName(originalName);
			String storePath = ResearchConstant.PROOF_STORE_PATH_ROOT+"/"+ genName;

			//设置旁证材料值
			proof.setUploadProofName(originalName);
			proof.setUploadRealName(genName);
			proof.setProofPath(storePath);
			proof.setUploadContentType(fileItem.getContentType());
			proof.setTimeProofUpload(new Date());
			proofList.add(proof);

			File file = new File(storePath);
			try {
				file.createNewFile();
				fileItem.write(file);
			}catch (IOException e) {		//TODO：文件读写异常处理？
				e.getStackTrace();
			}catch (Exception e) {
				e.getStackTrace();
			}
		}
		return proofList;
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

	public List<ResearchRecord> findSimpleListByUserAndClass(String userId, int classId) {
		return researchRecordDao.findListByUserAndClass(userId, classId);
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
		List<ResearchField> fields = researchFieldDao.findByClassId(classId);
		
		//按recordId对科研各种信息进行分类
		Map<String, List<ResearchData>> dataMap = convertMap(datas);
		Map<String, List<ResearchPerson>> personMap = convertMap(persons);
		Map<String, List<Proof>> proofMap = convertMap(proofs);
		
		//拼装科研信息
		for(ResearchRecord r : records) {
			String recordId = r.getId();
			Set<ResearchData> dataSet = new HashSet<ResearchData>(dataMap.get(recordId));
			//将相应字段为空的数据加入Set集合
			for(ResearchField f : fields) {
				ResearchData data = new ResearchData();
				data.setField(f);
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
