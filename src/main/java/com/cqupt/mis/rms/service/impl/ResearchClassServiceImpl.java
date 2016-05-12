package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqupt.mis.rms.vo.ResultInfo;
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
	
	public ResultInfo<Object> addClass(ResearchClass researchClass) {
		//sortDao.sortBeforeAdd("research_class", researchClass.getOrder(), 0);
		ResultInfo<Object> resultInfo = new ResultInfo<Object>();
		ResearchClass classResult = researchClassDao.selectByNameAndPid(researchClass.getClassName(), researchClass.getParentId());
		if(classResult != null) {
			return new ResultInfo<Object>(false, "已经存在相同名字的科研类别或项目，请换个名字试试");
		}
		boolean result = researchClassDao.add(researchClass);
		return new ResultInfo<Object>(result, null);
	}

	public boolean deleteClass(int classId) {
		researchClassDao.deleteByPId(classId);
		researchClassDao.deleteByClassId(classId);

//		//删除成功后，重新排序
//		if(result1 && result2)
//			return sortDao.sortAfterDelete("research_class", researchClass.getOrder(), 0);
			
		return true;
	}

	public ResultInfo<Object> modifyClass(ResearchClass researchClass) {
		//获取旧的科研类别信息
//		ResearchClass oldClass = researchClassDao.selectByPrimaryKey(researchClass.getClassId());
//		if(oldClass == null) {
//			return false;
//		}
		
		//维护排序字段
//		int oldOrder = oldClass.getOrder();
//		int newOrder = researchClass.getOrder();
//		boolean result = false;
//		if(oldOrder > newOrder) {
//			result = sortDao.sortForModify1("research_class", oldOrder, newOrder, 0);
//		}else if(oldOrder < newOrder) {
//			result = sortDao.sortForModify2("research_class", oldOrder, newOrder, 0);
//		}else if(oldOrder == newOrder) {
//			result = true;
//		}

		ResearchClass classResult = researchClassDao.selectByNameAndPid(researchClass.getClassName(), researchClass.getParentId());
		if(classResult != null) {
			return new ResultInfo<Object>(false, "修改科研类别异常！已经存在同名的科研类别名称，请换一个名称试试！");
		}

		boolean result = researchClassDao.modifyByPrimaryKey(researchClass);
		if(result) {
			return new ResultInfo<Object>(null, true);
		}else {
			return new ResultInfo<Object>(false, "修改科研类别失败！请稍后再试，或者联系管理员解决！");
		}
	}

	public List<ResearchClass> findAll() {
		return researchClassDao.findAll();
	}
	
}
