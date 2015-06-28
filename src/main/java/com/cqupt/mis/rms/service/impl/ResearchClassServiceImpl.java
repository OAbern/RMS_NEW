package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.ResearchClassDao;
import com.cqupt.mis.rms.dao.SortDao;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.service.ResearchClassService;

/**
 * 处理科研类别的逻辑层实现
 * @author Bern
 *
 */
@Service
public class ResearchClassServiceImpl implements ResearchClassService {
	@Resource
	private ResearchClassDao researchClassDao;
	
	@Resource
	private SortDao sortDao;
	
	public boolean addClass(ResearchClass researchClass) {
		sortDao.sortBeforeAdd("research_class", researchClass.getOrder(), 0);
		return researchClassDao.add(researchClass);
	}

	public boolean deleteClass(ResearchClass researchClass) {
		boolean result1 = researchClassDao.delete(researchClass.getClassId());
		
		if(result1)
			return sortDao.sortAfterDelete("research_class", researchClass.getOrder(), 0);
			
		return false;
	}

	public boolean modifyClass(ResearchClass researchClass) {
		//获取旧的科研类别信息
		ResearchClass oldClass = researchClassDao.selectByPrimaryKey(researchClass.getClassId());
		if(oldClass == null) {
			return false;
		}
		
		//维护排序字段
		int oldOrder = oldClass.getOrder();
		int newOrder = researchClass.getOrder();
		boolean result = false;
		if(oldOrder > newOrder) {
			result = sortDao.sortForModify1("research_class", oldOrder, newOrder, 0);
		}else if(oldOrder < newOrder) {
			result = sortDao.sortForModify2("research_class", oldOrder, newOrder, 0);
		}else if(oldOrder == newOrder) {
			result = true;
		}
		
		if(result)
			return researchClassDao.modifyByPrimaryKey(researchClass);
		
		return false;
	}

	public List<ResearchClass> findAll() {
		return researchClassDao.findAll();
	}
	
}
