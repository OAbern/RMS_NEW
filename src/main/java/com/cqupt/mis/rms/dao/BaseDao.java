package com.cqupt.mis.rms.dao;

import java.util.List;

/**
 * 所有Dao层接口的父类
 * @author welkin
 *  T 所有子类Dao的Model类型
 *  T1 model主键类型
 */
public interface BaseDao<T,T1> {

	/**
	 * 查询所有记录
	 * @return 结果集
	 */
	public List<T> findAll();

	/**
	 * 根据主键查找相应的记录
	 * @param id 主键
	 * @return 相应的结果
	 */
	public T selectByPrimaryKey(T1 id);

	/**
	 * 根据主键删除相应的记录
	 * @param id 主键
	 * @return 操作结果
	 */
	public boolean deleteByPrimaryKey(T1 id);
	
	/**
	 * 根据主键更新相应的记录
	 * @param id 主键
	 * @return 操作结果
	 */
	public boolean modifyByPrimaryKey(T obj);
	
	/**
	 * 添加记录
	 * @param obj 相应的类
	 * @return 操作结果
	 */
	public boolean add(T obj);

}
