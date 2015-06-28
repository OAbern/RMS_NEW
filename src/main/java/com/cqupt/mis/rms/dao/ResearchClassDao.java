package com.cqupt.mis.rms.dao;

import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchClass;

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
	public boolean delete(int classId);
}
