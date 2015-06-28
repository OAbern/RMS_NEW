package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 用户和角色、描述的基础类
 * @author Bern
 *
 */
public class RelationUserRole implements Serializable {
	private static final long serialVersionUID = -8765820702578675439L;
	
	private int id;
	private RelationUserRolePk relationUserRolePk;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RelationUserRolePk getRelationUserRolePk() {
		return relationUserRolePk;
	}

	public void setRelationUserRolePk(RelationUserRolePk relationUserRolePk) {
		this.relationUserRolePk = relationUserRolePk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
