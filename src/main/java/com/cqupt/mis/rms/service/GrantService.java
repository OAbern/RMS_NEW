package com.cqupt.mis.rms.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.vo.ResultInfo;

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
	 * @param rolePurviewDyns
	 */
	public boolean grant(int roleId,int resourceIdArr[],List<RolePurviewDyn> rolePurviewDyns);

	/**
	 * 通过roleId为该角色授静态资源权限
	 * @param roleId
	 * @param resourceIdArr
	 */
	public boolean grantFixed(int roleId,int resourceIdArr[]);

	/**
	 * 通过roleId为该角色授动态资源权限
	 * @param rolePurviewDyns
	 */
	public boolean grantDyn(List<RolePurviewDyn> rolePurviewDyns);

	/**
	 * 获取所有资源列表，以及角色对应的权限列表
	 * @param roleId 待查询的角色的roleId
	 * @return	各种列表将作为Object放入Map中
     */
	public Map<String, Object> getAuthority(int roleId);

	/**
	 * 进行授权操作
	 * @param map json转换的map
	 * @return 操作结果
     */
	public ResultInfo<Object> grant(JSONObject map);
	
}