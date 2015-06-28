package com.cqupt.mis.rms.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.service.UserManagerService;

/**
 * 自定义的登录校验器类
 * @author Bern
 *
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	{
		System.out.println("***********************5********************************");
	}
	private UserManagerService userManagerService;
	
	private static final String USERNAME = "userName";
	private static final String USERPWD = "userPwd";
	private static final String CHECK = "check";
	private static final String LOGINTYPE = "loginType";
	
	@Override  
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {  
        if (!request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());  
        }
        
        String userName = obtainUsername(request);
        String userPwd = obtainPassword(request);
        String check = obtainLoginCheck(request);
        int loginType = obtainLoginType(request);
        
     // 首先检查角色是否匹配
     	CQUPTRole role = userManagerService.findRoleLevel(userName, loginType);
     	if(role!= null){
     		String rand = (String)request.getSession().getAttribute("rand");
     		if (rand.equals(check)) {
     			// 角色和登录类型匹配，判断用户名和密码是否正确
     			boolean result = userManagerService.findUNameAndUPass(userName,userPwd);
     			if (result) {
     				// 用户名和密码正确,则保存登录名和用户角色信息——因为后面很多地方会使用到这两个参数，所以存放在session里面
     				request.getSession().setAttribute("userId", userName);
     				request.getSession().setAttribute("roleId", role.getRoleId());
     			} else {
     				request.getSession().setAttribute("loginFailed", "用户名或密码错误！");
     				throw new AuthenticationServiceException("用户名或密码错误！");
     			}
     		} else {
     			request.getSession().setAttribute("loginFailed", "请输入正确的验证码！");
     			throw new AuthenticationServiceException("请输入正确的验证码！");
     		}
     	}else {
     		request.getSession().setAttribute("loginFailed", "身份不匹配,请重新选择！");
     		throw new AuthenticationServiceException("身份不匹配,请重新选择！");
     	}
          
        //UsernamePasswordAuthenticationToken实现 Authentication  
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, userPwd);  
        // Place the last username attempted into HttpSession for views  
          
        // 允许子类设置详细属性  
        setDetails(request, authRequest);  
        
        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication  
        return this.getAuthenticationManager().authenticate(authRequest);  
    }
	
	@Override  
    protected String obtainUsername(HttpServletRequest request) {  
        Object obj = request.getParameter(USERNAME);  
        return null == obj ? "" : obj.toString();  
    }  
  
    @Override  
    protected String obtainPassword(HttpServletRequest request) {  
        Object obj = request.getParameter(USERPWD);  
        return null == obj ? "" : obj.toString();  
    }
    
    protected String obtainLoginCheck(HttpServletRequest request) {  
        Object obj = request.getParameter(CHECK);  
        return null == obj ? "" : obj.toString();
    }

    protected int obtainLoginType(HttpServletRequest request) {  
        int obj = Integer.parseInt(request.getParameter(LOGINTYPE));  
        return obj;
    }


}
