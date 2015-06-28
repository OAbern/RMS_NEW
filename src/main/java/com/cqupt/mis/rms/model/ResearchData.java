package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 科研记录的数据类
 * @author Bern
 *
 */
public class ResearchData implements Serializable {
	private static final long serialVersionUID = -8250804688789695187L;
	
	private String recordId;		//记录
	private ResearchFiled filed;		//字段
	private String value;		//字段的值
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + ((filed == null) ? 0 : filed.getId());
		return result;
	}
	
	/*
	 * 重写equals方法，判断相等的条件为：若field的id相同，则视为相等
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(getClass() != obj.getClass())
			return false;
		ResearchData other = (ResearchData) obj;
		if(this.filed == null)
			return false;
		if(other.filed == null)
			return false;
		if(this.filed.getId() == other.filed.getId()) {
			return true;
		} else {
			return false;
		}
		
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public ResearchFiled getFiled() {
		return filed;
	}

	public void setFiled(ResearchFiled filed) {
		this.filed = filed;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
