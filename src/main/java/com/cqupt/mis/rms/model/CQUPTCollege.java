package com.cqupt.mis.rms.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 学院信息的基础类
 * @author Bern
 *
 */
public class CQUPTCollege {
	// 学院Id
	private String collegeId;
	// 学院名字
	private String collegeName;
	//映射学院和学院角色信息链表
	private Set<RoleCollege> roleColleges = new HashSet<RoleCollege>();

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Set<RoleCollege> getRoleColleges() {
		return roleColleges;
	}

	public void setRoleColleges(Set<RoleCollege> roleColleges) {
		this.roleColleges = roleColleges;
	}

}
