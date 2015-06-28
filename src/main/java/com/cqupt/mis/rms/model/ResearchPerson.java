package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 学生获奖信息对应的指导教师类
 * @author Bern
 *
 */
public class ResearchPerson implements Serializable {
	private static final long serialVersionUID = 7699606715375343420L;
	
	private int id;		//逻辑主键
	private String recordId;		//记录
	private String userId;		//Id
	private String name;		//成员姓名
	private String remarks;		//备注
	private int order;		//排名
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
