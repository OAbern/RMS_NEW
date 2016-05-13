package com.cqupt.mis.rms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cqupt.mis.rms.dao.CQUPTCollegeDao;
import com.cqupt.mis.rms.model.CQUPTCollege;
import com.cqupt.mis.rms.model.CQUPTUser;
import com.cqupt.mis.rms.utils.JSONUtils;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.utils.SessionConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.mis.rms.service.UserManagerService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 处理用户信息的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
	@Resource
	UserManagerService userManagerServiceImpl;
	@Resource
	CQUPTCollegeDao cquptCollegeDao;

	/**
	 * 检查用户密码是否正确
	 * @param pw 密码
	 * @param request @see HttpServletRequest
	 * @param response @see HttpServletResponse
     */
	@RequestMapping(value="/checkpw", method=RequestMethod.POST)
	public void checkPW(@RequestParam("pw")String pw, HttpServletRequest request, HttpServletResponse response) {
		String userId = (String) request.getSession().getAttribute(SessionConstant.USERID);
		boolean result = userManagerServiceImpl.findUNameAndUPass(userId, pw);
		JSONUtils.toJSON(result, response);
	}

	/**
	 * 修改用户密码
	 * @param oldPW 用户原始密码
	 * @param newPW 用户新密码
	 * @param session @see HttpSession
     * @return 定向到结果视图
     */
	@RequestMapping("/modifypw")
	public ModelAndView modifyPW(String oldPW, String newPW, HttpSession session) {
		String userId = (String)session.getAttribute(SessionConstant.USERID);
		ResultInfo<Object> result = userManagerServiceImpl.modifyPW(userId, oldPW, newPW);
		return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
	}

	/**
	 * 获取用户信息
	 * @param session @see HttpSession
	 * @param response @see HttpServletResponse
     */
	@RequestMapping("/getuserinfo")
	public void getUserInfo(HttpSession session, HttpServletResponse response) {
		String userId = (String) session.getAttribute(SessionConstant.USERID);
		CQUPTUser user = userManagerServiceImpl.findUserById(userId);
		JSONUtils.toJSONWithNull(user, response);
	}

	/**
	 * 修改用户信息
	 * @param cquptUser 待修改的用户信息
	 * @param session HttpSession
	 * @return 定向到结果视图
     */
	@RequestMapping("/modifyuserinfo")
	public ModelAndView modifyUserInfo(CQUPTUser cquptUser, HttpSession session) {
		String userId = (String)session.getAttribute(SessionConstant.USERID);
		cquptUser.setUserId(userId);
		ResultInfo<Object> result = userManagerServiceImpl.modifyUserInfo(cquptUser);
		return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
	}

	/**
	 * 获取学院信息
	 * @param response @see HttpServletResponse
     */
	@RequestMapping("/getcollege")
	public void getCollege(HttpServletResponse response) {
		List<CQUPTCollege> collegeList = cquptCollegeDao.findAll();
		JSONUtils.toJSON(collegeList, response);
	}
}
