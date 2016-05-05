package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.service.CQUPTRoleService;

/**
 * 处理角色信息的逻辑层实现
 * @author Bern
 */
@Service
public class CQUPTRoleServiceImpl implements CQUPTRoleService {
	@Resource
	private CQUPTRoleDao cquptRoleDao;
	
	public List<CQUPTRole> findAll() {
		return cquptRoleDao.findAll();
	}

	public ResultInfo<Object> add(CQUPTRole role) {
		Object obj = cquptRoleDao.findByName(role.getRoleName());
		if(obj != null) {
			return new ResultInfo<Object>(false, "已经有相同的角色名存在，请换一个试试吧！");
		}
		boolean reslut = cquptRoleDao.add(role);
		if(reslut) {
			return new ResultInfo<Object>(null, true);
		}else {
			return new ResultInfo<Object>(false, "添加角色信息失败！请稍后再试，或者联系管理员解决！");
		}
	}

	public ResultInfo<Object> deleteByRoleId(int roleId) {
		//TODO: 已经分配角色的用户处理？
		boolean result = cquptRoleDao.deleteByPrimaryKey(roleId);
		if(result) {
			return new ResultInfo<Object>(null, true);
		}else {
			return new ResultInfo<Object>(false, "删除角色信息失败！");
		}
	}

	public ResultInfo<Object> modifyRole(CQUPTRole role) {
		boolean result = false;
		CQUPTRole originRole = cquptRoleDao.selectByPrimaryKey(role.getRoleId());
		if(originRole!=null && originRole.getRoleName().equals(role.getRoleName())) {		//没有改变角色名字
			result = cquptRoleDao.modifyByPrimaryKey(role);
		}else {			//检测角色名字是否重复
			Object obj = cquptRoleDao.findByName(role.getRoleName());
			if(obj != null) {		//检测到有重名，拒绝修改
				return new ResultInfo<Object>(false, "已经有相同的角色名字，请换一个试试！");
			}else {		//没有重名，开始修改
				result = cquptRoleDao.modifyByPrimaryKey(role);
			}
		}

		if(result) {
			return new ResultInfo<Object>(null, true);
		}else {
			return new ResultInfo<Object>(false, "修改角色名字失败！请稍后再试，或者联系管理员解决！");
		}
	}

}
