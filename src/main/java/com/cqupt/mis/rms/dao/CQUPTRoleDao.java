package com.cqupt.mis.rms.dao;

import java.util.List;
import java.util.Set;

import com.cqupt.mis.rms.model.UserAndRole;
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

	/**
	 * 查找所有的用户及其角色（包含极少的字段）
	 * @return 所有的用户及其角色
     */
	public List<UserAndRole> findAllUserAndRole();

	/**
	 * 分配用户角色（添加）
	 * @param userId 用户Id
	 * @param roleIdArrayAdd 添加的角色Id数组
     * @return 操作结果
     */
	public boolean assignRoleAdd(@Param("uId")String userId, @Param("roleIdArray")int[] roleIdArrayAdd);

	/**
	 * 分配用户角色（删除）
	 * @param userId 用户Id
	 * @param roleIdArrayDel 删除的角色Id
	 * @return 操作结果
	 */
	public boolean assignRoleDelete(@Param("uId")String userId, @Param("roleIdArray")int[] roleIdArrayDel);

	/**
	 * 查找指定用户的所有角色Id
	 * @param userId 待查找用户的Id
	 * @return 查找到的角色Id数组
     */
	public Integer[] findRoleIdByUserId(@Param("uId")String userId);
}