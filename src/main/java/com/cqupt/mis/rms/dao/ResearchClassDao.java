package com.cqupt.mis.rms.dao;

import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchClass;

import java.util.List;

/**
 * 处理科研信息类别的Dao层接口
 * @author Bern
 *
 */
@Repository("researchClassDao")
public interface ResearchClassDao extends BaseDao<ResearchClass, Integer> {
	
	/**
	 * 根据类别Id进行假删除
	 * @param classId
	 * @return 操作结果
	 */
	public boolean deleteByClassId(int classId);

	/**
	 * 根据parent_id假删除同一大类
	 * @return 操作结果
     */
	public boolean deleteByPId(int pId);

	/**
	 * 查找一级动态资源信息(parentId为0)
	 * @return 一级动态资源信息列表
     */
	public List<ResearchClass> selectFirstClass();
}
