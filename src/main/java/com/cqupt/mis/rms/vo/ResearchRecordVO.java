package com.cqupt.mis.rms.vo;

import java.io.Serializable;

/**
 * 科研记录类的值对象
 * 简单的对应数据库表
 * @author Bern
 *
 */
public class ResearchRecordVO implements Serializable {
	private static final long serialVersionUID = 9168690756724857381L;
	
	private String recordId;		//记录id
	private int classId;		//记录所属的类别
	private String returnReason;		//未通过审核的原因
	private int status;		//记录的状态
	private String submitUserId;		//记录的提交者的id
	private String approvedUserId;		//记录的审核者的id
	
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSubmitUserId() {
		return submitUserId;
	}
	public void setSubmitUserId(String submitUserId) {
		this.submitUserId = submitUserId;
	}
	public String getApprovedUserId() {
		return approvedUserId;
	}
	public void setApprovedUserId(String approvedUserId) {
		this.approvedUserId = approvedUserId;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
