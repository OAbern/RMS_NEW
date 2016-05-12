package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchField;

/**
 * 处理科研动态字段的Dao层接口
 * @author Bern
 *
 */
@Repository
public interface ResearchFieldDao extends BaseDao<ResearchField, Integer>{
	
	/**
	 * 根据classId查找所有科研字段
	 * @param classId
	 * @return	符合条件的结果
	 */
	public List<ResearchField> findByClassId(int classId);

	/**
	 * 根据classId查找所有科研字段
	 * @param fieldId
	 * @return	符合条件的结果
	 */
	public ResearchField findByFieldId(int fieldId);
	
	/**
	 * 根据fieldId对科研动态字段进行假删除
	 * @param fieldId
	 * @return 操作结果
	 */
	public boolean delete(int fieldId);

	/**
	 * 根据classId统计当前科研项目的字段数
	 * @param classId 待查询的科研项目classId
	 * @return 总共未删除的字段数
     */
	public Object countByClassId(int classId);

	/**
	 * 检测字段数据库名是否有重名
	 * @param classId 字段所属类别
	 * @param name 待检测字段数据名
     * @return 查找结果，若无重名返回null，若有返回此字段
     */
	public ResearchField checkNameBeforeAdd(@Param("cId")int classId, @Param("name")String name);

	/**
	 * 检测字段展示名是否有重名
	 * @param classId 字段所属类别
	 * @param des 待检测字段展示名
     * @return 查找结果，若无重名返回null，若有返回此字段
     */
	public ResearchField checkDesBeforeAdd(@Param("cId")int classId, @Param("des")String des);
}
