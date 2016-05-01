package com.cqupt.mis.rms.model;

import com.cqupt.mis.rms.utils.ResearchConstant;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	private Set<ResearchData> fields;		//记录的动态字段数据
	private List<Proof> proofs;
	private List<ResearchPerson> persons;
	
	private CQUPTUser submitUser;		//记录的提交者
	private CQUPTUser approvedUser;		//记录的审核者
	private Date submitTime;		//提交时间
	private String submitTimeString;		//方便人阅读时间格式
	
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
		return fields;
	}
	public void setFields(Set<ResearchData> fields) {
		this.fields = fields;
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

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * 获取适合人阅读的时间格式
	 * @return
     */
	public String getSubmitTimeString() {
		SimpleDateFormat format = new SimpleDateFormat(ResearchConstant.DATE_FORMAT_PATTERN);
		submitTimeString = format.format(submitTime);
		return submitTimeString;
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
		
		if(fields != null) {
			StringBuilder temp = new StringBuilder();
			temp.append("{ [");
			for(ResearchData d : fields) {
				temp.append("{ \"value\":\" "+d.getValue()+"\" }, ");
			}
			fieldsJson = temp.substring(0, temp.length()-2);
			fieldsJson = fieldsJson +" ] }";
		}
//		System.out.println("fieldJson:"+fieldsJson);
		return fieldsJson;
	}
	
}
