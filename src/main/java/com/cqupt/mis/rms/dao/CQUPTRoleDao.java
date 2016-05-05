package com.cqupt.mis.rms.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.ResourceInfo;

/**
 * 角色管理模块接口
 * @author welkin
 *
 */
@Repository
public interface CQUPTRoleDao extends BaseDao<CQUPTRole, Integer>{
	
	/**
	 * 根据角色Id查找静态资源
	 * @param roleId 待查找的角色id
	 * @return 角色信息
	 */
	public  CQUPTRole findRolePurviewByRoleId(int roleId);
	
	/**
	 * 根据角色Id和父类资源查找静态资源
	 * @param roleId 待查找的角色id
	 * @return 角色信息
	 */
	public CQUPTRole findRolePurviewByRoleIdAndParent(@Param("roleId")int roleId,@Param("parentId")int parentId);
	
	/**
	 * 根据角色Id查找动态资源
	 * @param roleId 待查找的角色id
	 * @return 角色信息
	 */
	public  CQUPTRole findRolePurviewDynRoleId(int roleId);

	/**
	 * 根据用户Id和角色等级Id查找相应的用户角色是否存在
	 * @param userId
	 * @param roleLevelId
	 * @return
	 */
	public  CQUPTRole findRoleLevel(@Param("userId")String userId,@Param("roleLevelId")int roleLevelId);

	/**
	 * 根据名字查找角色
	 * @param roleName 角色名字
	 * @return 角色信息
     */
	public CQUPTRole findByName(@Param("rName")String roleName);
	
   
}