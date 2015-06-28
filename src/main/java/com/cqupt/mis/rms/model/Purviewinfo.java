package com.cqupt.mis.rms.model;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Title:用户的模块信息
 * </p>
 * <p>
 * Copyright:Copyright(c)2012
 * </p>
 * <p>
 * Company:重邮信管工作室
 * </p>
 * 
 * @author LM
 * @version 1.0
 * */
public class Purviewinfo {
	// 模块Id
	private int purviewId;
	// 模块名字
	private String purviewName;
	// 模块链接地址
	private String purviewUrl;
	// 模块说明
	private String purviewRemark;
	// 模块父类信息
	private Purviewinfo parentPurviewinfo;
	// 与角色进行多对一关联
	private Set<RolePurview> RolePurviews = new HashSet<RolePurview>();

	public int getPurviewId() {
		return purviewId;
	}

	public void setPurviewId(int purviewId) {
		this.purviewId = purviewId;
	}

	public String getPurviewName() {
		return purviewName;
	}

	public void setPurviewName(String purviewName) {
		this.purviewName = purviewName;
	}

	public String getPurviewUrl() {
		return purviewUrl;
	}

	public void setPurviewUrl(String purviewUrl) {
		this.purviewUrl = purviewUrl;
	}

	public String getPurviewRemark() {
		return purviewRemark;
	}

	public void setPurviewRemark(String purviewRemark) {
		this.purviewRemark = purviewRemark;
	}

	public Purviewinfo getParentPurviewinfo() {
		return parentPurviewinfo;
	}

	public void setParentPurviewinfo(Purviewinfo parentPurviewinfo) {
		this.parentPurviewinfo = parentPurviewinfo;
	}

	public Set<RolePurview> getRolePurviews() {
		return RolePurviews;
	}

	public void setRolePurviews(Set<RolePurview> RolePurviews) {
		this.RolePurviews = RolePurviews;
	}

}
