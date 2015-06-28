package com.cqupt.mis.rms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.RolePurviewDao;
import com.cqupt.mis.rms.dao.RolePurviewDynDao;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.GrantService;

@Service("grantService")
public class GrantServiceImpl implements GrantService {
	
	@Resource
	private RolePurviewDao rolePurviewDao;
	@Resource
	private RolePurviewDynDao rolePurviewDynDao;

	public boolean grant(int roleId, int[] resourceIdArr,List<RolePurviewDyn> rolePurviewDyns) {
		
		if(grantFixed(roleId, resourceIdArr)&&grantDyn(rolePurviewDyns))
			return true;
		
		return false;
		
	
	}

	public boolean grantFixed(int roleId, int[] resourceIdArr) {
		
		return rolePurviewDao.addRolePurview(roleId, resourceIdArr);
		
		
	}

	public boolean grantDyn(List<RolePurviewDyn> rolePurviewDyns) {
		return rolePurviewDynDao.addRolePurviewDyn(rolePurviewDyns);
	}

}
