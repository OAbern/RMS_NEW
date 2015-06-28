package com.cqupt.mis.rms.model;

import java.io.Serializable;

/**
 * 用户基本信息的基础类
 * @author Bern
 *
 */
public class CQUPTUser implements Serializable {
	private static final long serialVersionUID = -125537152166680132L;
	
	private String userId;
	// 映射和用户登录信息表之间的一对一关联关系
	private UserLogin userLogin;
	private CQUPTCollege cquptCollege;
	private String department;
	private String userName;
	private String gender;
	private String origin;
	private String nationality;
	private String birthday;
	private String politicalStatus;
	private String timeJoinParty;
	private String timeBeginCqupt;
	private String timeBeginWork;
	private String firstDegree;
	private String firstProfessionalName;
	private String firstGraduateSchool;
	private String lastDegree;
	private String lastProfessionalName;
	private String lastGraduateSchool;
	private String lastAcademic;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getTimeJoinParty() {
		return timeJoinParty;
	}

	public void setTimeJoinParty(String timeJoinParty) {
		this.timeJoinParty = timeJoinParty;
	}

	public String getTimeBeginCqupt() {
		return timeBeginCqupt;
	}

	public void setTimeBeginCqupt(String timeBeginCqupt) {
		this.timeBeginCqupt = timeBeginCqupt;
	}

	public String getTimeBeginWork() {
		return timeBeginWork;
	}

	public void setTimeBeginWork(String timeBeginWork) {
		this.timeBeginWork = timeBeginWork;
	}

	public String getFirstDegree() {
		return firstDegree;
	}

	public void setFirstDegree(String firstGegree) {
		this.firstDegree = firstGegree;
	}

	public String getFirstProfessionalName() {
		return firstProfessionalName;
	}

	public void setFirstProfessionalName(String firstProfessionalName) {
		this.firstProfessionalName = firstProfessionalName;
	}

	public String getFirstGraduateSchool() {
		return firstGraduateSchool;
	}

	public void setFirstGraduateSchool(String firstGraduateSchool) {
		this.firstGraduateSchool = firstGraduateSchool;
	}

	public String getLastDegree() {
		return lastDegree;
	}

	public void setLastDegree(String lastDegree) {
		this.lastDegree = lastDegree;
	}

	public String getLastProfessionalName() {
		return lastProfessionalName;
	}

	public void setLastProfessionalName(String lastProfessionalName) {
		this.lastProfessionalName = lastProfessionalName;
	}

	public String getLastGraduateSchool() {
		return lastGraduateSchool;
	}

	public void setLastGraduateSchool(String lastGraduateSchool) {
		this.lastGraduateSchool = lastGraduateSchool;
	}

	public String getLastAcademic() {
		return lastAcademic;
	}

	public void setLastAcademic(String lastAcademic) {
		this.lastAcademic = lastAcademic;
	}

	public CQUPTCollege getCquptCollege() {
		return cquptCollege;
	}

	public void setCquptCollege(CQUPTCollege cquptCollege) {
		this.cquptCollege = cquptCollege;
	}

}
