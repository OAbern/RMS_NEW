package com.cqupt.mis.rms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.mis.rms.service.UserManagerService;

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
	
	@RequestMapping("/loginchech")
	public void login() {
		
	}
	
	
}
