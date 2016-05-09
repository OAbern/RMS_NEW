package com.cqupt.mis.rms.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.dao.UserLoginDao;
import com.cqupt.mis.rms.model.CQUPTCollege;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.CQUPTUser;
import com.cqupt.mis.rms.model.UserAndRole;
import com.cqupt.mis.rms.model.UserLogin;
import com.cqupt.mis.rms.service.UserManagerService;
import com.cqupt.mis.rms.utils.EncryptUtils;

/**
 * Title:管理用户信息的服务曾接口实现类
 * @author welkin
 * */
// 注入管理用户信息的底层接口
@Service
public class UserManagerServiceImpl implements UserManagerService {
	@Resource
	private UserLoginDao userLoginDao;
	@Resource 
	private CQUPTRoleDao cquptRoleDao;
	
	public boolean addUserLoginAndCquptUser(UserLogin uLogin, CQUPTUser cUser) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean findUNameAndUPass(String userId, String userpwd) {
		userpwd = EncryptUtils.setUPassEncrypt(userpwd);
		UserLogin userLogin = userLoginDao.findUNameAndUPass(userId, userpwd);
		if(userLogin != null)
			return true;
		
		return false;
	}

	public CQUPTRole findRoleLevel(String userId,int roleLevelId) {
		CQUPTRole cquptRole = cquptRoleDao.findRoleLevel(userId, roleLevelId);
		return cquptRole;
	}

	public ResultInfo<Object> modifyPW(String userId, String oldPW, String newPW) {
		oldPW = EncryptUtils.setUPassEncrypt(oldPW);
		newPW = EncryptUtils.setUPassEncrypt(newPW);
		UserLogin userLogin = userLoginDao.findUNameAndUPass(userId, oldPW);
		if(userLogin == null) {
			return new ResultInfo<Object>(false, "用户的原始密码不正确！");
		}

		boolean result = userLoginDao.modifyPW(userId, newPW);
		if(result) {
			return new ResultInfo<Object>(null, true);
		}else {
			return new ResultInfo<Object>(false, "修改密码失败！请稍后再试，或者联系管理员解决！");
		}
	}

	public boolean readUserBasicInfoExceltoDB(File excelfile) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<CQUPTUser> getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public CQUPTCollege getCQUPTCollege(String collegeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean findUserId(String UserId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UserAndRole> findCQUPTUserListByUserIdAndRoleId(
			String loginUserId, int loginUserRoleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addUserRoleInfo(String userID, int[] RoleIdArr) {
		// TODO Auto-generated method stub
		return false;
	}
}
