package com.cqupt.mis.rms.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.MenuInfo;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.model.ResourceInfo;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.MenuService;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private CQUPTRoleDao cquptRoleDao; 
	public List<MenuInfo> findMenuList(int roleId) {
		
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		
		List<ResearchClass> researchClassInput = new LinkedList<ResearchClass>();
		List<ResearchClass> researchClassManage = new LinkedList<ResearchClass>();
		List<ResearchClass> researchClassApprove = new LinkedList<ResearchClass>();
		List<ResearchClass> researchClassStatistics = new LinkedList<ResearchClass>();
		//开始获取动态资源的菜单
		CQUPTRole cquptRoleDyn = cquptRoleDao.findRolePurviewDynRoleId(roleId);
		Set<RolePurviewDyn> rolePurviewDyns = cquptRoleDyn.getRolePurviewDyns();
		Iterator<RolePurviewDyn> itDyn = rolePurviewDyns.iterator();
		while(itDyn.hasNext()){
			RolePurviewDyn rolePurviewDyn= itDyn.next();
			if(rolePurviewDyn.isInput()){
				researchClassInput.add(rolePurviewDyn.getResearchClass());
			}
			if(rolePurviewDyn.isManage()){
				researchClassManage.add(rolePurviewDyn.getResearchClass());
			}
			if(rolePurviewDyn.isApprove()){
				researchClassApprove.add(rolePurviewDyn.getResearchClass());
			}
			if(rolePurviewDyn.isStatistics()){
				researchClassStatistics.add(rolePurviewDyn.getResearchClass());
			}
			
		}
		
		
		//开始获取静态资源一级菜单
		CQUPTRole cquptRole = findMenuOneList(roleId);
		Set<RolePurview> rolePurviews = cquptRole.getRolePurviews();
		Iterator<RolePurview> it = rolePurviews.iterator();
		while(it.hasNext()){
			RolePurview rolePurview =  it.next();
			ResourceInfo resourceInfo = rolePurview.getPurviewinfo();
			
			//开始获取静态资源二级菜单
			int parentId = resourceInfo.getResourceId();
			CQUPTRole cquptRole2 = findMenuTwoList(roleId, parentId);
			Set<RolePurview> rolePurviews2 = cquptRole2.getRolePurviews();
			List<ResourceInfo> resourceInfos2 = new LinkedList<ResourceInfo>();
			Iterator<RolePurview> it2 = rolePurviews2.iterator();
			while(it2.hasNext()){
				resourceInfos2.add(it2.next().getPurviewinfo());
			}
			
			
			//开始把获取的静态菜单信息放入model中便于前台使用
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setFirst(resourceInfo);
			menuInfo.setSecond(resourceInfos2);
			//开始把获取的动态态菜单信息放入model中便于前台使用
			if(parentId==12){
				menuInfo.setSecondDyn(researchClassInput);
			}else if(parentId==14){
				menuInfo.setSecondDyn(researchClassManage);
			}else if(parentId==15){
				menuInfo.setSecondDyn(researchClassApprove);
			}else if(parentId==16){
				menuInfo.setSecondDyn(researchClassStatistics);
			}
			
			menuInfos.add(menuInfo);
			
		}	
		
		return menuInfos;
	}

	public CQUPTRole findMenuOneList(int roleId) {
		
		CQUPTRole cquptRole = cquptRoleDao.findRolePurviewByRoleIdAndParent(roleId,0);
		
			return cquptRole;
	}

	public CQUPTRole findMenuTwoList(int roleId, int parentId) {
		CQUPTRole cquptRole = cquptRoleDao.findRolePurviewByRoleIdAndParent(roleId,parentId);
		
		return cquptRole;
	}

}
