package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.CQUPTRole;

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
	 
}
