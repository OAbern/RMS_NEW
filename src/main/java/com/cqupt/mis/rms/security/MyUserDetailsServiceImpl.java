package com.cqupt.mis.rms.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.cqupt.mis.rms.dao.UserLoginDao;
import com.cqupt.mis.rms.model.UserLogin;
import com.cqupt.mis.rms.model.UserRoleInfo;
import com.cqupt.mis.rms.utils.EncryptUtils;


/**
 * 实现SpringSecurity的UserDetailsService接口,获取用户Detail信息(用户名，密码，状态信息，用户权限)
 * 用户详细信息管理：数据源、用户缓存（通过数据库管理用户、角色、权限、资源）。
 * @author welkin
 *
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {
	{
		System.out.println("***********************4********************************");
	}
	@Resource
	private UserLoginDao userLoginDao;
	
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		
		//根据用户名查找用户的权限信息
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		UserLogin userLogin = userLoginDao.findCQUPTRoleByUserId(userId);
		Set<UserRoleInfo> Set1 = userLogin.getUserRoleInfo();
		 
		for (UserRoleInfo userRoleInfo : Set1) {
			GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(userRoleInfo.getRoleinfo().getRoleId()));
			auths.add(authority);
		}
		
		/*
		 * 根据用户名得到用户密码等信息,必须返回已解密的密码
		 */
		String password = EncryptUtils.getDesString(userLogin.getUserPwd());	
		
		return new User(userLogin.getUserId(), password, true, true, true, true, auths);
	}

	
}
