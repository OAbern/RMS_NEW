package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.vo.ResultInfo;

/**
 * 处理角色信息的逻辑层接口
 * @author Bern
 *
 */
public interface CQUPTRoleService {
	/**
	 * 获取所有的角色信息列表
	 * @return
	 */
	public List<CQUPTRole> findAll();

	/**
	 * 添加角色信息
	 * @param role 角色信息
	 * @return 操纵结果
     */
	public ResultInfo<Object> add(CQUPTRole role);

	/**
	 * 根据角色ID删除角色
	 * @param roleId 角色Id
	 * @return 操作结果
     */
	public ResultInfo<Object> deleteByRoleId(int roleId);

	/**
	 * 修改角色信息
	 * @param role 角色信息
	 * @return	操作结果
     */
	public ResultInfo<Object> modifyRole(CQUPTRole role);
	 
}
