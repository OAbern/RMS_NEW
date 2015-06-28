package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.ResearchFiled;

/**
 * 处理科研动态字段的逻辑层接口
 * @author Bern
 *
 */
public interface ResearchFiledService {
	/**
	 * 添加科研动态字段
	 * @param researchClass
	 * @return
	 */
	public boolean addFiled(ResearchFiled researchFiled);
	
	/**
	 * 删除科研动态字段(假删除)
	 * @param filedId
	 * @return
	 */
	public boolean deleteFiled(ResearchFiled researchFiled);
	
	/**
	 * 修改科研动态字段
	 * @param researchField
	 * @return
	 */
	public boolean modifyFiled(ResearchFiled researchFiled);
	
	/**
	 * 根据classId查找所有的科研动态字段
	 * @param 
	 * @return
	 */
	public List<ResearchFiled> findByClassId(int classId);
}
