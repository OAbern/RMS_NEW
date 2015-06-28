package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
	动态资源信息的基础类
* @author Welkin
*
*/
public class RolePurviewDyn implements Serializable {
	private static final long serialVersionUID = 6604549633890745986L;
	
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
	
}
