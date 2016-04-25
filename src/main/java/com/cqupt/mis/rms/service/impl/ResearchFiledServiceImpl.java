package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqupt.mis.rms.utils.ResearchConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.ResearchFiledDao;
import com.cqupt.mis.rms.dao.SortDao;
import com.cqupt.mis.rms.model.ResearchFiled;
import com.cqupt.mis.rms.service.ResearchFiledService;

/**
 * 处理科研动态的逻辑层实现
 * @author Bern
 *
 */
@Service
public class ResearchFiledServiceImpl implements ResearchFiledService {
	@Resource
	private ResearchFiledDao researchFiledDao;
	
	@Resource
	private SortDao sortDao;

	public ResultInfo<Object> addFiled(ResearchFiled researchFiled) {
		boolean result;
		int classId = researchFiled.getResearchClass().getClassId();
		//维护排序字段
		sortDao.sortBeforeAdd(ResearchConstant.TABLE_R_FIELD, researchFiled.getOrder(), classId);

		Object o1 = researchFiledDao.checkNameBeforeAdd(classId, researchFiled.getName());
		Object o2 = researchFiledDao.checkDesBeforeAdd(classId, researchFiled.getDescription());
		if(o1==null && o2==null) {		//没有重名
			result = researchFiledDao.add(researchFiled);
		}else {
			return new ResultInfo<Object>(false, "重名冲突！请确认字段数据库名或字段的展示名没有与已存在的字段重复！");
		}

		if(result) {
			return new ResultInfo<Object>(null, true);
		}else {
			return  new ResultInfo<Object>(false, "字段添加失败，请重新尝试！");
		}
	}

	public boolean deleteFiled(ResearchFiled researchFiled) {
		boolean result = researchFiledDao.delete(researchFiled.getId());
		
		if(result)
			return sortDao.sortAfterDelete("research_filed", researchFiled.getOrder(), researchFiled.getResearchClass().getClassId());		
		
		return false;
	}

	public boolean modifyFiled(ResearchFiled researchField) {
		//获取旧的科研字段
		ResearchFiled oldFiled = researchFiledDao.selectByPrimaryKey(researchField.getId());
		if(oldFiled == null) {
			return false;
		}
		
		//维护排序字段
		int oldOrder = oldFiled.getOrder();
		int newOrder = researchField.getOrder();
		boolean result = false;
		if(oldOrder > newOrder) {
			result = sortDao.sortForModify1("research_filed", oldOrder, newOrder, researchField.getResearchClass().getClassId());
		}else if(oldOrder < newOrder) {
			result = sortDao.sortForModify2("research_filed", oldOrder, newOrder, researchField.getResearchClass().getClassId());
		}else if(oldOrder == newOrder) {
			result = true;
		}
		
		if(result)
			return researchFiledDao.modifyByPrimaryKey(researchField);
			
		return false;
	}

	public List<ResearchFiled> findByClassId(int classId) {
		return researchFiledDao.findByClassId(classId);
	}

	
}
