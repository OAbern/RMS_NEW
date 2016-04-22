package com.cqupt.mis.rms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.MenuInfo;
import com.cqupt.mis.rms.service.CQUPTRoleService;
import com.cqupt.mis.rms.service.MenuService;
import com.cqupt.mis.rms.service.UserManagerService;
import com.cqupt.mis.rms.utils.JSONUtils;
import com.cqupt.mis.rms.utils.SessionConstant;

/**
 * 处理登录相关信息的控制器类（Filter不进行拦截）
 * @author Bern
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private CQUPTRoleService cquptRoleServiceImpl;
	@Resource
	private UserManagerService userManagerServiceImpl;
	@Resource
	private MenuService menuServiceImpl;
	
	/**
	 * 获取所有角色列表
	 */
	@RequestMapping("/findRoleList")
	public void findRoleList(HttpServletResponse response) {
		List<CQUPTRole> roles = cquptRoleServiceImpl.findAll();
		JSONUtils.toJSON(roles, response);
	}


	
	/**
	 * 登录校验
	 */
	@RequestMapping("/check")
	public ModelAndView loginCheck(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();

		//TODO： 方便测试代码，跳过登录检验
		request.getSession().setAttribute(SessionConstant.USEID, "1");
		request.getSession().setAttribute(SessionConstant.ROLEID, "1");
		view.setViewName("redirect:/login/menu.do");
		if(true)
			return view;
		//TODO： 方便测试代码，跳过登录检验

		String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String check = request.getParameter("check");
        int loginType = Integer.parseInt(request.getParameter("loginType"));
        
        String rand = (String)request.getSession().getAttribute("rand");
        if(rand == null) {		//长时间为挂起网页，导致session失效
        	view.setViewName("login.jsp");
        	request.setAttribute("loginFailed", "验证码过期，请重试！");
        	return view;
        }
        
 		if (rand.equals(check)) {	//检查验证码
 			CQUPTRole role = userManagerServiceImpl.findRoleLevel(userName, loginType);
 			if(role!= null){	// 检查角色是否匹配
     			boolean result = userManagerServiceImpl.findUNameAndUPass(userName,userPwd);
     			if (result) {	// 角色和登录类型匹配，判断用户名和密码是否正确
     				// 用户名和密码正确,则保存登录名和用户角色信息——因为后面很多地方会使用到这两个参数，所以存放在session里面
     				request.getSession().setAttribute(SessionConstant.USEID, userName);
     				request.getSession().setAttribute(SessionConstant.ROLEID, role.getRoleId());
     				view.setViewName("redirect:/pages/common/main.html");
     			} else {
     				request.setAttribute("loginFailed", "用户名或密码错误！");
     				view.setViewName("login.jsp");
     			}
     		} else {
     			request.setAttribute("loginFailed", "身份不匹配,请重新选择！");
         		view.setViewName("login.jsp");
     		}
     	} else {
     		request.setAttribute("loginFailed", "请输入正确的验证码！");
 			view.setViewName("login.jsp");
     	}
     	return view;
	}
	
	@RequestMapping("/menu")
	public void getMenu(HttpServletRequest request, HttpServletResponse response) {
		Object menuObj = request.getSession().getAttribute(SessionConstant.MENU_INFO);
		if(menuObj == null) {		//从数据库获取数据
			Object roleIdObj = request.getSession().getAttribute(SessionConstant.ROLEID);
			if(roleIdObj == null) {
				return;
				//TODO: session 失效？
			}
			int roleId = Integer.parseInt(roleIdObj.toString());
			List<MenuInfo> menu = menuServiceImpl.findMenuList(roleId);
			if (menu.size() != 0) {
				request.getSession().setAttribute(SessionConstant.MENU_INFO, menu);
			}
			System.out.println(menu);
			JSONUtils.toJSON(menu, response);
		}else {		//从session缓存获取数据
			JSONUtils.toJSON(menuObj, response);
		}

	}
	
	
}
