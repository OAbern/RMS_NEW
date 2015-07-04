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
	 * @param roleId
	 * @return 
	 */
	public  CQUPTRole findRolePurviewByRoleId(int roleId);
	
	/**
	 * 根据角色Id和父类资源查找静态资源
	 * @param roleId
	 * @return 
	 */
	public CQUPTRole findRolePurviewByRoleIdAndParent(@Param("roleId")int roleId,@Param("parentId")int parentId);
	
	/**
	 * 根据角色Id查找动态资源
	 * @param roleId
	 * @return 
	 */
	public  CQUPTRole findRolePurviewDynRoleId(int roleId);

	/**
	 * 根据用户Id和角色等级Id查找相应的用户角色是否存在
	 * @param userId
	 * @param roleLevelId
	 * @return
	 */
	public  CQUPTRole findRoleLevel(@Param("userId")String userId,@Param("roleLevelId")int roleLevelId);
	
	
	
   
}