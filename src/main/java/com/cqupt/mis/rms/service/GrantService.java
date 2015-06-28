package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.RolePurviewDyn;
/**
 * 授权管理逻辑接口层
 * @author welkin
 *
 */
public interface GrantService {
	/**
	 * 通过roleId为该角色授所有权限
	 * @param roleId
	 * @param resourceIdArr
	 * @param rolePurviewDyn
	 */
	public boolean grant(int roleId,int resourceIdArr[],List<RolePurviewDyn> rolePurviewDyns);
	/**
	 * 通过roleId为该角色授静态资源权限
	 * @param roleId
	 * @param resourceIdArr
	 * @param rolePurviewDyn
	 */
	public boolean grantFixed(int roleId,int resourceIdArr[]);
	/**
	 * 通过roleId为该角色授动态资源权限
	 * @param roleId
	 * @param resourceIdArr
	 * @param rolePurviewDyn
	 */
	public boolean grantDyn(List<RolePurviewDyn> rolePurviewDyns);
	
}
