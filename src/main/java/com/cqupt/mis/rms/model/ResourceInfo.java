package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 静态资源信息的基础类
 * @author Bern
 *
 */
public class ResourceInfo implements Serializable {
	private static final long serialVersionUID = -8916650917893946723L;
	
	// 资源Id
	private int resourceId;
	// 资源名字
	private String resourceName;
	// 资源链接地址
	private String resourceUrl;
	// 资源说明
	private String resourceRemark;
	// 资源父类信息
	private ResourceInfo parentResourceinfo;
	// 资源父类的id
	private int parentId;
	// 与角色进行多对一关联
	private Set<RolePurview> rolePurviews = new HashSet<RolePurview>();
	
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceRemark() {
		return resourceRemark;
	}
	public void setResourceRemark(String resourceRemark) {
		this.resourceRemark = resourceRemark;
	}
	public ResourceInfo getParentResourceinfo() {
		return parentResourceinfo;
	}
	public void setParentResourceinfo(ResourceInfo parentResourceinfo) {
		this.parentResourceinfo = parentResourceinfo;
	}
	public Set<RolePurview> getRolePurviews() {
		return rolePurviews;
	}
	public void setRolepurviews(Set<RolePurview> rolePurviews) {
		this.rolePurviews = rolePurviews;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
