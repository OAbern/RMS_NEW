package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;

@Repository
public interface RolePurviewDao  extends BaseDao<RolePurview, Integer>{
	/**
	 * 根据角色ID添加动态权限
	 * @param roleId 角色id
	 * @param resourceIdArr 增加的资源id数组
	 * @return
	 */
	public boolean addRolePurview(@Param("roleId")int roleId,@Param("resourceIdArr")int[] resourceIdArr);

	/**
	 * 根据角色Id查找静态资源的权限
	 * @param roleId 待查找的角色Id
	 * @return 相应的角色Id列表
     */
	public List<RolePurview> findByRoleId(@Param("roleId")int roleId);

	/**
	 * 根据角色ID删除动态权限
	 * @param roleId 角色id
	 * @param resourceIdArr 删除的资源id数组
	 * @return
	 */
	public boolean deleteRolePurview(@Param("roleId")int roleId,@Param("resourceIdArr")int[] resourceIdArr);
}
