package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqupt.mis.rms.utils.ResearchConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.dao.SortDao;
import com.cqupt.mis.rms.model.ResearchField;
import com.cqupt.mis.rms.service.ResearchFieldService;

/**
 * 处理科研动态的逻辑层实现
 * @author Bern
 *
 */
@Service
public class ResearchFieldServiceImpl implements ResearchFieldService {
	@Resource
	private ResearchFieldDao researchFieldDao;
	
	@Resource
	private SortDao sortDao;

	public ResultInfo<Object> addField(ResearchField researchField) {
		boolean result;
		int classId = researchField.getResearchClass().getClassId();
		//维护排序字段
		sortDao.sortBeforeAdd(ResearchConstant.TABLE_R_FIELD, researchField.getOrder(), classId);

		Object o1 = researchFieldDao.checkNameBeforeAdd(classId, researchField.getName());
		Object o2 = researchFieldDao.checkDesBeforeAdd(classId, researchField.getDescription());
		if(o1==null && o2==null) {		//没有重名
			result = researchFieldDao.add(researchField);
		}else {
			return new ResultInfo<Object>(false, "重名冲突！请确认字段数据库名或字段的展示名没有与已存在的字段重复！");
		}

		if(result) {
			return new ResultInfo<Object>(null, true);
		}else {
			return  new ResultInfo<Object>(false, "字段添加失败，请重新尝试！");
		}
	}

	public boolean deleteField(ResearchField researchField) {
		boolean result = researchFieldDao.delete(researchField.getId());
		
		if(result)
			return sortDao.sortAfterDelete("research_field", researchField.getOrder(), researchField.getResearchClass().getClassId());
		
		return false;
	}

	public boolean modifyField(ResearchField researchField) {
		//获取旧的科研字段
		ResearchField oldField = researchFieldDao.selectByPrimaryKey(researchField.getId());
		if(oldField == null) {
			return false;
		}
		
		//维护排序字段
		int oldOrder = oldField.getOrder();
		int newOrder = researchField.getOrder();
		boolean result = false;
		if(oldOrder > newOrder) {
			result = sortDao.sortForModify1("research_field", oldOrder, newOrder, researchField.getResearchClass().getClassId());
		}else if(oldOrder < newOrder) {
			result = sortDao.sortForModify2("research_field", oldOrder, newOrder, researchField.getResearchClass().getClassId());
		}else if(oldOrder == newOrder) {
			result = true;
		}
		
		if(result)
			return researchFieldDao.modifyByPrimaryKey(researchField);
			
		return false;
	}

	public List<ResearchField> findByClassId(int classId) {
		return researchFieldDao.findByClassId(classId);
	}

	
}
