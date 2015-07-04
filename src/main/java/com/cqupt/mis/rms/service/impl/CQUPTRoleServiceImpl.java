package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.service.CQUPTRoleService;

/**
 * 处理角色信息的逻辑层实现
 * @author Bern
 *
 */
@Service
public class CQUPTRoleServiceImpl implements CQUPTRoleService {
	@Resource
	private CQUPTRoleDao cquptRoleDao;
	
	public List<CQUPTRole> findAll() {
		return cquptRoleDao.findAll();
	}

}
