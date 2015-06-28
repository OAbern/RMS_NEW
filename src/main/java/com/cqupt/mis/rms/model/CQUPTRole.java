package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息的基础类
 * @author Welkin
 *
 */
public class CQUPTRole implements Serializable {
	private static final long serialVersionUID = 3535854373112052041L;
	// 角色编号
	private int roleId;
	// 角色名字
	private String roleName;
	// 角色级别
	private RoleLevel roleLevel;
	// 角色说明
	private String description;
	// 映射和静态模块信息的一对多关联关系
	private Set<RolePurview> rolePurviews = new HashSet<RolePurview>();
	//映射和动态模块信息的一对多关联关系
	private Set<RolePurviewDyn> rolePurviewDyns = new HashSet<RolePurviewDyn>();


	// 映射角色和学院之间的一对多关联关系
	private Set<RoleCollege> roleColleges = new HashSet<RoleCollege>();


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RoleLevel getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(RoleLevel roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<RolePurview> getRolePurviews() {
		return rolePurviews;
	}

	public void setRolePurviews(Set<RolePurview> rolePurviews) {
		this.rolePurviews = rolePurviews;
	}

	public Set<RolePurviewDyn> getRolePurviewDyns() {
		return rolePurviewDyns;
	}

	public void setRolePurviewDyns(Set<RolePurviewDyn> rolePurviewDyns) {
		this.rolePurviewDyns = rolePurviewDyns;
	}
	
	public Set<RoleCollege> getRoleColleges() {
		return roleColleges;
	}

	public void setRoleColleges(Set<RoleCollege> roleColleges) {
		this.roleColleges = roleColleges;
	}

}
