package com.cqupt.mis.rms.utils;

/**
 * 记录session里面存储的常量的类
 * 所有存在session中的变量的名字都要在这里记录，并且说明用途
 * 获取session中的变量时，必须使用此处定义的常量名
 * @author Bern
 *
 */
public class SessionConstant {
	/**
	 * 用于session中记录用户名的变量名
	 */
	public static final String USEID = "useId";
	
	/**
	 * 用于session中记录角色ID的变量名
	 */
	public static final String ROLEID = "roleId";

	/**
	 * 用于session中记录角色相关的菜单信息
	 */
	public static final String MENU_INFO = "menuInfo";
	
}
