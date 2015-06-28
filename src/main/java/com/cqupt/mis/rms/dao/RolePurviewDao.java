package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;

@Repository
public interface RolePurviewDao  extends BaseDao<RolePurview, Integer>{
	/**
	 * 根据角色ID添加动态权限
	 * @param roleId 
	 * @return
	 */
	public boolean addRolePurview(@Param("roleId")int roleId,@Param("resourceIdArr")int resourceIdArr[]);
	

}
