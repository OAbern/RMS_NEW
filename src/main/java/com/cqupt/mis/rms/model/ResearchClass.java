package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 科研信息类别的基础类
 * @author Bern
 *
 */
public class ResearchClass implements Serializable {
	private static final long serialVersionUID = -6543494403621700544L;
	
	private int classId;
	private String className;		//类别的名字
	private String classDes;		//类别的描述信息
	private String classRemark;		//类别的提示信息
	private int isDelete;		//类别是否删除，0为未删除，1为删除
	private int order;		//类别展示顺序
	private Date submittime;		//类别提交时间
	private int parentId;		//类别的父类id
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDes() {
		return classDes;
	}
	public void setClassDes(String classDes) {
		this.classDes = classDes;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Date getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}
	public String getClassRemark() {
		return classRemark;
	}
	public void setClassRemark(String classRemark) {
		this.classRemark = classRemark;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
