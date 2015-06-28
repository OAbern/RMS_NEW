package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.dao.BaseDao;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;

/**
 * 角色权限动态信息的DAO层接口
 * @author Bern
 *
 */
@Repository
public interface RolePurviewDynDao extends BaseDao<RolePurviewDyn, Integer>{
	/**
	 * 根据角色ID查找动态权限
	 * @param roleId 
	 * @return
	 */
	public List<RolePurviewDyn> findByRoleId(@Param("rId")int roleId);
	
	/**
	 * 查找动态权限的所有记录
	 */
	public List<RolePurviewDyn> findAll();
	
	/**
	 * 根据角色ID添加动态权限
	 * @param roleId 
	 * @return
	 */
	public boolean addRolePurviewDyn(List<RolePurviewDyn> RolePurviewDyns);
}
