package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.MenuInfo;

/**
 * 加载菜单功能逻辑层
 * @author welkin
 *
 */
public interface MenuService {
	
	/**
	 * 根据角色Id加载全部的菜单项
	 * @param roleId 角色ID
	 * @return
	 */
	public List<MenuInfo>  findMenuList(int roleId);
	
	/**
	 * 根据角色Id加载一级的菜单项（parentId为0）
	 * @param roleId 角色ID
	 * @return
	 */
	public CQUPTRole findMenuOneList(int roleId);
	
	/**
	 * 根据角色Id和父类Id加载相应的二级的菜单项
	 * @param roleId 角色ID
	 * @return
	 */
	public CQUPTRole findMenuTwoList(int roleId,int parentId);
	
}
