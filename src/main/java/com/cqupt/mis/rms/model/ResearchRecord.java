package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 科研记录类
 * @author Bern
 *
 */
public class ResearchRecord implements Serializable {
	private static final long serialVersionUID = 6941581106011370864L;
	
	private String id;		//记录id
	private ResearchClass researchClass;		//记录所属的类别
	private String returnReason;		//未通过审核的原因
	private int status;		//记录的状态
	private String statusDes;
	private String fieldsJson;		//动态字段类的json格式
	
	private Set<ResearchData> fileds;		//记录的动态字段数据
	private List<Proof> proofs;
	private List<ResearchPerson> persons;
	
	private CQUPTUser submitUser;		//记录的提交者
	private CQUPTUser approvedUser;		//记录的审核者
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ResearchClass getResearchClass() {
		return researchClass;
	}
	public void setResearchClass(ResearchClass researchClass) {
		this.researchClass = researchClass;
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
	public Set<ResearchData> getFields() {
		return fileds;
	}
	public void setFields(Set<ResearchData> fields) {
		this.fileds = fields;
	}
	public CQUPTUser getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(CQUPTUser submitUser) {
		this.submitUser = submitUser;
	}
	public CQUPTUser getApprovedUser() {
		return approvedUser;
	}
	public void setApprovedUser(CQUPTUser approvedUser) {
		this.approvedUser = approvedUser;
	}public List<Proof> getProofs() {
		return proofs;
	}
	public void setProofs(List<Proof> proofs) {
		this.proofs = proofs;
	}
	public List<ResearchPerson> getPersons() {
		return persons;
	}
	public void setPersons(List<ResearchPerson> persons) {
		this.persons = persons;
	}

	
	/**
	 * 返回相应状态码的状态描述
	 * @return statusDes
	 */
	public String getStatusDes() {
		switch (status) {
		case 0:
			statusDes = "已保存";
			break;
		case 1:
			statusDes = "未审批";
			break;
		case 2:
			statusDes = "审批通过";
			break;
		case 3:
			statusDes = "审批未通过";
			break;
		default:
			statusDes = "";
			break;
		}
		return statusDes;
	}
	
	/**
	 * 返回字段集合的json格式(只有值)
	 * @return 
	 */
	public String getFieldsJson() {
		fieldsJson = "";
		
		if(fileds != null) {
			StringBuilder temp = new StringBuilder();
			temp.append("{ [");
			for(ResearchData d : fileds) {
				temp.append("{ \"value\":\" "+d.getValue()+"\" }, ");
			}
			fieldsJson = temp.substring(0, temp.length()-2);
			fieldsJson = fieldsJson +" ] }";
		}
		System.out.println("fieldJson:"+fieldsJson);
		return fieldsJson;
	}
	
}
