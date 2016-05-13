package com.cqupt.mis.rms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.UserLogin;

@Repository
public interface UserLoginDao extends BaseDao<UserLogin, String>{
	/**
	 * 通过userID查找用户相关的角色信息
	 * @param userId
	 * @return
	 */
	public UserLogin findCQUPTRoleByUserId(String userId);
	/**
	 * 通过用户id和密码去查找相应的用户登录信息
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	public UserLogin findUNameAndUPass(@Param("userId")String userId,@Param("userPwd") String userPwd);

	/**
	 * 修改用户密码
	 * @param userId 用户id
	 * @param userPwd 用户密码
     * @return 操作结果
     */
	public boolean modifyPW(@Param("userId")String userId,@Param("pw") String userPwd);

}