package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 科研信息的字段类
 * @author Bern
 *
 */
public class ResearchFiled implements Serializable {
	private static final long serialVersionUID = 4715805479464345311L;
	
	private int id;		//字段id
	private ResearchClass researchClass;		//字段所属的类别
	private String name;		//字段在数据库中的名字
	private String description;		//字段在页面展示的名字
	private int order;		//字段的展现顺序值
	private int isDelete;		//字段是否删除，0为未删除，1为删除
	private Date submittime;		//字段提交时间
	private int isNull;	//该字段录入时可否为空
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public ResearchClass getResearchClass() {
		return researchClass;
	}
	public void setResearchClass(ResearchClass researchClass) {
		this.researchClass = researchClass;
	}
	public int getIsNull() {
		return isNull;
	}
	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}
	
}
