package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

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

	public boolean addFiled(ResearchFiled researchFiled) {
		sortDao.sortBeforeAdd("research_filed", researchFiled.getOrder(), researchFiled.getResearchClass().getClassId());
		return researchFiledDao.add(researchFiled);
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
