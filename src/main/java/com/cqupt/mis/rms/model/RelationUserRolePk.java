package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 关联用户和角色的组合类
 * @author Bern
 *
 */
public class RelationUserRolePk implements Serializable {
	private static final long serialVersionUID = 6340661173838894140L;
	
	private CQUPTUser cquptUser;
	private CQUPTRole cquptRole;

	public CQUPTUser getCquptUser() {
		return cquptUser;
	}

	public void setCquptUser(CQUPTUser cquptUser) {
		this.cquptUser = cquptUser;
	}

	public CQUPTRole getCquptRole() {
		return cquptRole;
	}

	public void setCquptRole(CQUPTRole cquptRole) {
		this.cquptRole = cquptRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cquptRole == null) ? 0 : cquptRole.hashCode());
		result = prime * result
				+ ((cquptUser == null) ? 0 : cquptUser.hashCode());
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
		RelationUserRolePk other = (RelationUserRolePk) obj;
		if (cquptRole == null) {
			if (other.cquptRole != null)
				return false;
		} else if (!cquptRole.equals(other.cquptRole))
			return false;
		if (cquptUser == null) {
			if (other.cquptUser != null)
				return false;
		} else if (!cquptUser.equals(other.cquptUser))
			return false;
		return true;
	}

}
