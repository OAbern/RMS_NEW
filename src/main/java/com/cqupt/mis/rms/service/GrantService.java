package com.cqupt.mis.rms.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.apache.ibatis.annotations.Result;

/**
 * 授权管理逻辑接口层
 * @author welkin
 *
 */
public interface GrantService {

	/**
	 * 通过roleId为该角色授静态资源权限
	 * @param roleId 角色id
	 * @param resourceIdArr 静态资源id数组
	 * @return 操作结果
	 */
	public boolean grantFixed(int roleId,int resourceIdArr[]);

	/**
	 * 通过roleId为该角色授动态资源权限
	 * @param rolePurviewDyns 动态资源权限列表
	 * @return 操作结果
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
	 * @param map @see JSONObject <p>json字符串转换的JSONObject（内含HashMap）</p>
	 * @return 操作结果
     */
	public ResultInfo<Object> grant(JSONObject map);

	/**
	 * 给指定用户分配角色
	 * @param userId 指定的用户Id
	 * @param roleIdArray 角色id数组
     * @return 操作结果
     */
	public boolean assignRole(String userId, int[] roleIdArray);
	
}