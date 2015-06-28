package com.cqupt.mis.rms.service;

import java.io.File;
import java.util.List;

import com.cqupt.mis.rms.model.CQUPTCollege;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.CQUPTUser;
import com.cqupt.mis.rms.model.UserLogin;
import com.cqupt.mis.rms.model.UserAndRole;

/**
 * <p>Title:管理用户信息的服务层接口</p>
 * @author welkin
 * */
public interface UserManagerService {
	/**
	 * 添加教师登录信息和基本信息
	 * @param uLogin 教师登录实体
	 * @param cUser 教师基本信息实体
	 * @return boolean 成功返回true，失败返回false
	 * */
	public boolean addUserLoginAndCquptUser(UserLogin uLogin,CQUPTUser cUser);
	
	/**
	 * 判断用户密码是否正确
	 * @param userId  用户登录Id
	 * @param userpwd 用户密码
	 * @return UserLogin 获得的用户信息
	 * */
	public boolean findUNameAndUPass(String userId,String userpwd);
	/**
	 * 检查用户是否具有选中的角色级别——辅助用户登录所用
	 * @param roleLevelId 角色级别编号
	 * @param userId 用户登录ID
	 * @return CQUPTRole 获取的角色信息
	 * */
	public CQUPTRole findRoleLevel(String userId,int roleLevelId);
	/**
	 * 从EXCEL表格里面导入教师基本信息进入数据库
	 * @param excelfile EXCEL文件
	 * @return  导入成功返回true  失败返回false
	 */
	public boolean readUserBasicInfoExceltoDB(File excelfile);
	/**
	 * 得到所有用户信息
	 * @return
	 */
	public List<CQUPTUser> getUser();
	/**
	 * 得到登录用户对象
	 * @param id用户id
	 * @return
	 */
	public CQUPTCollege getCQUPTCollege(String collegeId);
	/**
	 * 检查用户ID是否重复
	 * @param UserId
	 * @return true不重复
	 */
	public boolean findUserId(String UserId);
	
	/**
	 * 根据用户Id和角色Id，查询用户在该角色下面所能管理的用户基本信息和其对应的角色信息集合
	 * @param loginUserId 登录用户编号
	 * @param loginUserRoleId 登录用户角色Id
	 * @return 用户角色链表
	 * */
	public List<UserAndRole> findCQUPTUserListByUserIdAndRoleId(String loginUserId,int loginUserRoleId);
	/**
	 * 为某个员工添加多个角色
	 * @param userID 员工Id
	 * @param RoleIdArr 需要添加的角色集合
	 * */
	public boolean addUserRoleInfo(String userID,int[] RoleIdArr);
}
