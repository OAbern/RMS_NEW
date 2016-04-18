package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
	动态资源信息的基础类
* @author Welkin
*
*/
public class RolePurviewDyn implements Serializable {
	private static final long serialVersionUID = 6604549633890745986L;
	//逻辑主键
	private int id;
	// 角色实体
	private CQUPTRole roleInfo;
	// 资源信息实体
	private ResearchClass researchClass;
	//录入的权限
	private boolean input;
	//管理的权限
	private boolean manage;
	//审批的权限
	private boolean approve;
	//统计的权限
	private boolean statistics;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CQUPTRole getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(CQUPTRole roleInfo) {
		this.roleInfo = roleInfo;
	}
	public ResearchClass getResearchClass() {
		return researchClass;
	}
	public void setResearchClass(ResearchClass researchClass) {
		this.researchClass = researchClass;
	}
	public void setInput(boolean input) {
		this.input = input;
	}
	public void setManage(boolean manage) {
		this.manage = manage;
	}
	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	public void setStatistics(boolean statistics) {
		this.statistics = statistics;
	}
	public boolean isInput() {
		return input;
	}
	public boolean isManage() {
		return manage;
	}
	public boolean isApprove() {
		return approve;
	}
	public boolean isStatistics() {
		return statistics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleInfo == null) ? 0 : roleInfo.hashCode());
		result = prime * result
				+ ((researchClass == null) ? 0 : researchClass.hashCode());
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

		RolePurviewDyn other = (RolePurviewDyn) obj;
		if (roleInfo == null) {
			if (other.roleInfo != null)
				return false;
		} else if (!roleInfo.equals(other.roleInfo))
			return false;

		if (researchClass == null) {
			if (other.researchClass != null)
				return false;
		} else if (!researchClass.equals(other.researchClass))
			return false;

		return true;
	}
	
}
