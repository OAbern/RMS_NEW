package com.cqupt.mis.rms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchFiled;

/**
 * 处理科研动态字段的Dao层接口
 * @author Bern
 *
 */
@Repository
public interface ResearchFiledDao extends BaseDao<ResearchFiled, Integer>{
	
	/**
	 * 根据classId查找所有科研字段
	 * @param classId
	 * @return	符合条件的结果
	 */
	public List<ResearchFiled> findByClassId(int classId);
	
	/**
	 * 根据fieldId对科研动态字段进行假删除
	 * @param filedId
	 * @return 操作结果
	 */
	public boolean delete(int filedId);
}
