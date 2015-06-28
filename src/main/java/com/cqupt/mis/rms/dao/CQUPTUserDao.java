package com.cqupt.mis.rms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.CQUPTUser;
/**
 * 用户管理模块Dao层
 * @author welkin
 *
 */
@Repository
public interface CQUPTUserDao extends BaseDao<CQUPTUser, Integer>{
	/**
	 * 通过collegeID查找相关的用户
	 * @param collegeId
	 * @return
	 */
	public List<CQUPTUser> findCQUPTUserByCollegeId(int collegeId);
	/**
	 * 通过userID数组批量删除用户
	 * @param array
	 */
	public void deleteByArray(String array[]);
}