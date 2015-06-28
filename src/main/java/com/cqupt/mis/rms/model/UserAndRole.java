/**
 * 
 */
package com.cqupt.mis.rms.model;

import java.util.List;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.CQUPTUser;

/**
 * 用户和角色信息
 * 
 * @author Welkin
 * 
 */
public class UserAndRole {
	// 用户基本信息
	private CQUPTUser user;
	// 用户角色信息
	private List<CQUPTRole> roleList;

	public CQUPTUser getUser() {
		return user;
	}

	public void setUser(CQUPTUser user) {
		this.user = user;
	}

	public List<CQUPTRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<CQUPTRole> roleList) {
		this.roleList = roleList;
	}

}
