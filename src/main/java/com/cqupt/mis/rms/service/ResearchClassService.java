package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.vo.ResultInfo;

/**
 * 处理科研类的服务接口层
 * @author Bern
 *
 */
public interface ResearchClassService {
	
	/**
	 * 添加科研类别
	 * @param researchClass
	 * @return
	 */
	public ResultInfo<Object> addClass(ResearchClass researchClass);
	
	/**
	 * 删除科研类别(假删除)
	 * @param classId
	 * @return
	 */
	public boolean deleteClass(ResearchClass researchClass);
	
	/**
	 * 修改科研类别
	 * @param researchClass
	 * @return
	 */
	public boolean modifyClass(ResearchClass researchClass);
	
	/**
	 * 查找所有的科研信息类别
	 * @return
	 */
	public List<ResearchClass> findAll();
}
