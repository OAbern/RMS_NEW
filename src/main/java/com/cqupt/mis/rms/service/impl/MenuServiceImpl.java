package com.cqupt.mis.rms.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.cqupt.mis.rms.dao.ResearchClassDao;
import com.cqupt.mis.rms.model.*;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
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

	@Resource
	private ResearchClassDao researchClassDao;
	
	public List<MenuInfo> findMenuList(int roleId) {
		
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();	//要返回的菜单结果集

		menuInfos.addAll(findDynamicMenuInfo(roleId));
		menuInfos.addAll(findFixedMenuInfo(roleId));

		return menuInfos;
	}

	/**
	 * 查找动态权限资源信息
	 * @return 返回动态权限信息的菜单
     */
	public List<MenuInfo> findDynamicMenuInfo(int roleId) {
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();	//要返回的菜单结果集

		List<ResourceInfo> input = new ArrayList<ResourceInfo>();	//录入科研信息的菜单
		List<ResourceInfo> manage = new ArrayList<ResourceInfo>();	//管理科研信息的菜单
		List<ResourceInfo> approve = new ArrayList<ResourceInfo>();	//审批科研信息的菜单
		List<ResourceInfo> statistics = new ArrayList<ResourceInfo>();	//统计查询科研信息的菜单

		//开始获取动态资源的菜单
		CQUPTRole cquptRoleDyn = cquptRoleDao.findRolePurviewDynRoleId(roleId);
		Set<RolePurviewDyn> rolePurviewDyns = cquptRoleDyn.getRolePurviewDyns();
		//生成动态资源的URL和名称
		Iterator<RolePurviewDyn> itDyn = rolePurviewDyns.iterator();
		while(itDyn.hasNext()){
			RolePurviewDyn rolePurviewDyn= itDyn.next();
			int classId = rolePurviewDyn.getResearchClass().getClassId();
			int parentId = rolePurviewDyn.getResearchClass().getParentId();
			String className = rolePurviewDyn.getResearchClass().getClassName();
			if(rolePurviewDyn.isInput()){	//判断是否有录入权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setParentId(parentId);
				info.setResourceUrl(ResearchConstant.getInputUrl(classId));
				info.setResourceName(ResearchConstant.getInputName(className));
				input.add(info);
			}
			if(rolePurviewDyn.isManage()){	//判断是否有管理个人权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setParentId(parentId);
				info.setResourceUrl(ResearchConstant.getViewManageListUrl(classId));
				info.setResourceName(ResearchConstant.getManageName(className));
				manage.add(info);
			}
			if(rolePurviewDyn.isApprove()){	//判断是否有审批权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setParentId(parentId);
				info.setResourceUrl(ResearchConstant.getApproveUrl(classId));
				info.setResourceName(ResearchConstant.getApproveName(className));
				approve.add(info);
			}
			if(rolePurviewDyn.isStatistics()){	//判断是否有查询统计权限，并转换为ResourceInfo
				ResourceInfo info = new ResourceInfo();
				info.setParentId(parentId);
				info.setResourceUrl(ResearchConstant.getStatisticsUrl(classId));
				info.setResourceName(ResearchConstant.getStatisticsName(className));
				statistics.add(info);
			}

		}

		//将动态权限资源信息按parentId分类
		List<ResearchClass> dynamicFirstList = researchClassDao.selectFirstClass();
		for(ResearchClass rClass : dynamicFirstList) {
			int parentId = rClass.getClassId();

			//录入分类
			List<ResourceInfo> inputByParentIdList = new ArrayList<ResourceInfo>();
			for(ResourceInfo info : input) {
				if(parentId == info.getParentId()) {
					inputByParentIdList.add(info);
				}
			}
			if(inputByParentIdList.size() != 0) {
				ResourceInfo dynamicMenuInputFirst = new ResourceInfo();
				dynamicMenuInputFirst.setResourceId(rClass.getClassId());
				dynamicMenuInputFirst.setResourceName(ResearchConstant.getInputName(rClass.getClassName()));

				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setFirst(dynamicMenuInputFirst);
				menuInfo.setSecond(inputByParentIdList);
				menuInfos.add(menuInfo);
			}

			//管理个人分类
			List<ResourceInfo> manageByParentIdList = new ArrayList<ResourceInfo>();
			for(ResourceInfo info : manage) {
				if(parentId == info.getParentId()) {
					manageByParentIdList.add(info);
				}
			}
			if(manageByParentIdList.size() != 0) {
				ResourceInfo dynamicMenuManageFirst = new ResourceInfo();
				dynamicMenuManageFirst.setResourceId(rClass.getClassId());
				dynamicMenuManageFirst.setResourceName(ResearchConstant.getManageName(rClass.getClassName()));

				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setFirst(dynamicMenuManageFirst);
				menuInfo.setSecond(manageByParentIdList);
				menuInfos.add(menuInfo);
			}

			//审批分类
			List<ResourceInfo> approveByParentIdList = new ArrayList<ResourceInfo>();
			for(ResourceInfo info : approve) {
				if(parentId == info.getParentId()) {
					approveByParentIdList.add(info);
				}
			}
			if(approveByParentIdList.size() != 0) {
				ResourceInfo dynamicMenuApproveFirst = new ResourceInfo();
				dynamicMenuApproveFirst.setResourceId(rClass.getClassId());
				dynamicMenuApproveFirst.setResourceName(ResearchConstant.getApproveName(rClass.getClassName()));

				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setFirst(dynamicMenuApproveFirst);
				menuInfo.setSecond(approveByParentIdList);
				menuInfos.add(menuInfo);
			}

			//查询统计分类
			List<ResourceInfo> statisticsByParentIdList = new ArrayList<ResourceInfo>();
			for(ResourceInfo info : statistics) {
				if(parentId == info.getParentId()) {
					statisticsByParentIdList.add(info);
				}
			}
			if(statisticsByParentIdList.size() != 0) {
				ResourceInfo dynamicMenuStatisticsFirst = new ResourceInfo();
				dynamicMenuStatisticsFirst.setResourceId(rClass.getClassId());
				dynamicMenuStatisticsFirst.setResourceName(ResearchConstant.getStatisticsName(rClass.getClassName()));

				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setFirst(dynamicMenuStatisticsFirst);
				menuInfo.setSecond(statisticsByParentIdList);
				menuInfos.add(menuInfo);
			}

		}

		return menuInfos;
	}

	/**
	 * 查找静态权限资源信息
	 * @return 返回静态权限信息的菜单
     */
	public List<MenuInfo> findFixedMenuInfo(int roleId) {
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();	//要返回的菜单结果集

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
