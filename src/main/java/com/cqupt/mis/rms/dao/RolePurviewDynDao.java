package com.cqupt.mis.rms.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.dao.BaseDao;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;

/**
 * 角色权限动态信息的DAO层接口
 * @author Bern
 *
 */
@Repository
public interface RolePurviewDynDao extends BaseDao<RolePurviewDyn, Integer>{
	/**
	 * 根据角色ID查找动态权限（包括级联信息）
	 * @param roleId 待查找的角色Id
	 * @return 动态权限列表
	 */
	public List<RolePurviewDyn> findByRoleId(@Param("rId")int roleId);

	/**
	 * 根据角色Id查找简单的动态权限信息（不包括级联信息）
	 * @param roleId 待查找的角色Id
	 * @return 动态权限列表
     */
	public List<RolePurviewDyn> findSimpleByRoleId(@Param("rId")int roleId);
	
	/**
	 * 查找动态权限的所有记录
	 */
	public List<RolePurviewDyn> findAll();
	
	/**
	 * 根据角色ID添加动态权限
	 * @return
	 */
	public boolean addRolePurviewDyn(List<RolePurviewDyn> RolePurviewDyns);

	/**
	 * 根据角色id 和 类别id 查找记录
	 * @param roleId
	 * @param classId
     * @return
     */
	public RolePurviewDyn findSimpleByRoleIdAndClassId(@Param("rId")int roleId, @Param("cId")int classId);

	/**
	 * 根据角色id和类别id更新记录
	 * @param object
	 * @return
     */
	public boolean updateByRoleIdAndClassId(@Param("rId")int roleId, @Param("map")JSONObject object);

	public boolean addOne(@Param("rId")int roleId, @Param("map")JSONObject object);
}
