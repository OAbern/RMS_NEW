package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 角色权限信息的基础类
 * @author Bern
 *
 */
public class RolePurview implements Serializable {
	private static final long serialVersionUID = -1731681913328864875L;
	
	// 角色实体
	private CQUPTRole roleInfo;
	// 资源信息实体
	private ResourceInfo purviewInfo;

	public CQUPTRole getRoleinfo() {
		return roleInfo;
	}

	public void setRoleinfo(CQUPTRole roleinfo) {
		this.roleInfo = roleinfo;
	}

	public ResourceInfo getPurviewinfo() {
		return purviewInfo;
	}

	public void setPurviewinfo(ResourceInfo purviewinfo) {
		this.purviewInfo = purviewinfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleInfo == null) ? 0 : roleInfo.hashCode());
		result = prime * result
				+ ((purviewInfo == null) ? 0 : purviewInfo.hashCode());
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
		RolePurview other = (RolePurview) obj;
		if (roleInfo == null) {
			if (other.roleInfo != null)
				return false;
		} else if (!roleInfo.equals(other.roleInfo))
			return false;
		if (purviewInfo == null) {
			if (other.purviewInfo != null)
				return false;
		} else if (!purviewInfo.equals(other.purviewInfo))
			return false;
		return true;
	}
}
