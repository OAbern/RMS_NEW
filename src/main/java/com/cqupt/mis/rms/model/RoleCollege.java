package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 角色学院信息的基础类
 * @author Bern
 *
 */
public class RoleCollege implements Serializable {
	private static final long serialVersionUID = 4165865688218723898L;
	
	// 角色信息
	private CQUPTRole roleInfo;
	// 学院信息
	private CQUPTCollege collegeInfo;

	public CQUPTRole getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(CQUPTRole roleInfo) {
		this.roleInfo = roleInfo;
	}

	public CQUPTCollege getCollegeInfo() {
		return collegeInfo;
	}

	public void setCollegeInfo(CQUPTCollege collegeInfo) {
		this.collegeInfo = collegeInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleInfo == null) ? 0 : roleInfo.hashCode());
		result = prime * result
				+ ((collegeInfo == null) ? 0 : collegeInfo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleCollege other = (RoleCollege) obj;
		if (roleInfo == null) {
			if (other.roleInfo != null)
				return false;
		} else if (!roleInfo.equals(other.roleInfo))
			return false;
		if (collegeInfo == null) {
			if (other.collegeInfo != null)
				return false;
		} else if (!collegeInfo.equals(other.collegeInfo))
			return false;
		return true;
	}
}
