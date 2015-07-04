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
import com.cqupt.mis.rms.model.ResourceInfo;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.MenuService;
import com.cqupt.mis.rms.utils.ResearchConstant;

/**
 * 加载菜单功能的逻辑层实现
 * @author Bern
 *
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private CQUPTRoleDao cquptRoleDao; 
	
	public List<MenuInfo> findMenuList(int roleId) {
		
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();	//要返回的菜单结果集
		
		List<ResourceInfo> input = new ArrayList<ResourceInfo>();	//录入科研信息的菜单
		List<ResourceInfo> manage = new ArrayList<ResourceInfo>();	//管理科研信息的菜单
		List<ResourceInfo> approve = new ArrayList<ResourceInfo>();	//审批科研信息的菜单
		List<ResourceInfo> statistics = new ArrayList<ResourceInfo>();	//统计查询科研信息的菜单
		
		//开始获取动态资源的菜单
		CQUPTRole cquptRoleDyn = cquptRoleDao.findRolePurviewDynRoleId(roleId);
		Set<RolePurviewDyn> rolePurviewDyns = cquptRoleDyn.getRolePurviewDyns();
		Iterator<RolePurviewDyn> itDyn = rolePurviewDyns.iterator();
		while(itDyn.hasNext()){
			RolePurviewDyn rolePurviewDyn= itDyn.next();
			int classId = rolePurviewDyn.getResearchClass().getClassId();
			String className = rolePurviewDyn.getResearchClass().getClassName();
			if(rolePurviewDyn.isInput()){	//判断是否有录入权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setResourceUrl(ResearchConstant.getInputUrl(classId));
				info.setResourceName(ResearchConstant.getInputName(className));
				input.add(info);
			}
			if(rolePurviewDyn.isManage()){	//判断是否有管理个人权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setResourceUrl(ResearchConstant.getManageUrl(classId));
				info.setResourceName(ResearchConstant.getManageName(className));
				manage.add(info);
			}
			if(rolePurviewDyn.isApprove()){	//判断是否有审批权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setResourceUrl(ResearchConstant.getApproveUrl(classId));
				info.setResourceName(ResearchConstant.getApproveName(className));
				approve.add(info);
			}
			if(rolePurviewDyn.isStatistics()){	//判断是否有查询统计权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setResourceUrl(ResearchConstant.getStatisticsUrl(classId));
				info.setResourceName(ResearchConstant.getStatisticsName(className));
				statistics.add(info);
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
			List<ResourceInfo> resourceInfos2 = null;
			if(cquptRole2 != null) {
				Set<RolePurview> rolePurviews2 = cquptRole2.getRolePurviews();
				resourceInfos2 = new LinkedList<ResourceInfo>();
				Iterator<RolePurview> it2 = rolePurviews2.iterator();
				while(it2.hasNext()){
					resourceInfos2.add(it2.next().getPurviewinfo());
				}
			}
			
			//开始把获取的静态菜单信息放入model中便于前台使用
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setFirst(resourceInfo);
			menuInfo.setSecond(resourceInfos2);
			//开始把获取的动态态菜单信息放入model中便于前台使用
			if(parentId == 2){
				menuInfo.setSecond(input);
			}else if(parentId == 3){
				menuInfo.setSecond(manage);
			}else if(parentId == 4){
				menuInfo.setSecond(approve);
			}else if(parentId == 5){
				menuInfo.setSecond(statistics);
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
