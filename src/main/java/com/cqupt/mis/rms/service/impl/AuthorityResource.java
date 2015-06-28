package com.cqupt.mis.rms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.dao.RolePurviewDynDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;

/**
 * 加载权限资源的类
 * @author Bern
 *
 */
@Service
public class AuthorityResource {
	@Resource
	private CQUPTRoleDao cquptRoleDao;
	@Resource
	private RolePurviewDynDao rolePurviewDynDao;
	
	private Map<String, List<Integer>> fixedResourceMap = null;	//资源URL为key， 对应的角色id为value
	
	private Map<Integer, Map<Integer, RolePurviewDyn>> dynamicResourceMap = null;	//外层map中：类别id为key， 权限为value；内层map中：角色id为key，权限为value

	public AuthorityResource() {}
	
	/**
	 * 加载静态的角色资源权限列表
	 */
	public void loadFixed() {
		List<CQUPTRole> allRole = cquptRoleDao.findAll();	//获取全部角色

		/*
		 * 资源URL为key， 对应的角色id为value。 一个资源可以由多个角色来访问。
		 */
		fixedResourceMap = new HashMap<String, List<Integer>>();
		
		for (CQUPTRole role : allRole) {
			int roleId = role.getRoleId();
			
			CQUPTRole cquptRole = cquptRoleDao.findRolePurviewByRoleId(roleId);
			
			Set<RolePurview> RolePurviewList = cquptRole.getRolePurviews();		//获取相应角色的相应合法资源信息	
			
			for (RolePurview RolePurview : RolePurviewList) {
				String url = RolePurview.getPurviewinfo().getResourceUrl();		
				
				/*
				 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				 */
				if(url == null) {	//一级菜单url为空！url检验匹配时会报空指针错误
					url = "NoURL";
					if (fixedResourceMap.containsKey(url)) {
						continue;
					}
				}
				if (fixedResourceMap.containsKey(url)) {
					List<Integer> list = fixedResourceMap.get(url);
					list.add(roleId);
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(roleId);
					fixedResourceMap.put(url, list);
				}
			}
		}
		
	}
	
	/**
	 * 加载动态的角色资源权限列表
	 */
	public void loadDynamic() {
		//获取数据库的动态权限列表
		List<RolePurviewDyn> rolePurviewDyns = rolePurviewDynDao.findAll();
		
		dynamicResourceMap = new HashMap<Integer, Map<Integer, RolePurviewDyn>>();
		
		//将动态权限列表重新封装到HashMap中，以便于查询
		for(RolePurviewDyn dyn : rolePurviewDyns) {
			int classId = dyn.getResearchClass().getClassId();
			int roleId = dyn.getRoleInfo().getRoleId();
			if(dynamicResourceMap.containsKey(classId)) {
				Map<Integer, RolePurviewDyn> map = dynamicResourceMap.get(classId);
				map.put(roleId, dyn);
			} else {
				Map<Integer, RolePurviewDyn> map = new HashMap<Integer, RolePurviewDyn>();
				map.put(roleId, dyn);
				dynamicResourceMap.put(classId, map);
			}
		}
		
	}

	public Map<String, List<Integer>> getFixedResourceMap() {
		if(fixedResourceMap == null) {
			loadFixed();
		}
		return fixedResourceMap;
	}

	public Map<Integer, Map<Integer, RolePurviewDyn>> getDynamicResourceMap() {
		if(dynamicResourceMap == null) {
			loadDynamic();
		}
		return dynamicResourceMap;
	}
	
	
}
