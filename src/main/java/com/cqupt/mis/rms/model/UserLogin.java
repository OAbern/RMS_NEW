package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户登录信息类
 * @author Bern
 *
 */
public class UserLogin implements Serializable {
	private static final long serialVersionUID = 63224850374082238L;
	
	// 员工ID
	private String userId;
	// 登录密
	private String userPwd;
	//映射和用户角色信息表之间的的多对一关联关系
	private Set<UserRoleInfo> userRoleInfo = new HashSet<UserRoleInfo>();
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Set<UserRoleInfo> getUserRoleInfo() {
		return userRoleInfo;
	}

	public void setUserRoleInfo(Set<UserRoleInfo> userRoleInfo) {
		this.userRoleInfo = userRoleInfo;
	}
	
}

