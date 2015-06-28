package com.cqupt.mis.rms.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.dao.RolePurviewDynDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.model.RolePurviewDyn;

/**
 * 该类主要用于获取相应的访问相应资源所需要的权限
 * 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色去访问。
 * @author welkin
 *
 */
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, ApplicationContextAware {
	{
		System.out.println("***********************1********************************");
	}
	private static ApplicationContext applicationContext;
	
	@Resource
	private CQUPTRoleDao cquptRoleDao;
	@Resource
	private RolePurviewDynDao rolePurviewDynDao;
	
	private Map<String, Collection<ConfigAttribute>> fixedResourceMap = null;	//资源为key， 权限为value
	
	private Map<Integer, Map<Integer, RolePurviewDyn>> dynamicResourceMap = null;	//外层map中：类别id为key， 权限为value；内层map中：角色id为key，权限为value
	
	public MyFilterInvocationSecurityMetadataSource() {}

	/**
	 * 加载静态的角色资源权限列表
	 */
	public void loadOnStartup1() {
		List<CQUPTRole> allRole = cquptRoleDao.findAll();	//获取全部角色？？

		/*
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		 */
		fixedResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		for (CQUPTRole role : allRole) {
			ConfigAttribute ca = new SecurityConfig(String.valueOf(role.getRoleId()));
			
			CQUPTRole cquptRole = cquptRoleDao.findRolePurviewByRoleId(role.getRoleId());
			
			List<RolePurview> RolePurviewList = (List<RolePurview>) cquptRole.getRolePurviews();		//获取相应角色的相应合法资源信息	
			
			for (RolePurview RolePurview : RolePurviewList) {
				String url = RolePurview.getPurviewinfo().getResourceUrl();		
				
				/*
				 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				 */
				if(url == null) {	//一级菜单url为空！url检验匹配时会报空指针错误		本类117行
					url = "NoURL";
					if (fixedResourceMap.containsKey(url)) {
						continue;
					}
				}
				if (fixedResourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = fixedResourceMap.get(url);
					value.add(ca);
					fixedResourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					fixedResourceMap.put(url, atts);
				}
			}
		}
		
	}
	
	/**
	 * 加载动态的角色资源权限列表
	 */
	public void loadOnStartup2() {
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
	
	/**
	 * 根据URL，找到相关的权限配置。
	 * @return Collection<ConfigAttribute> 返回相应url的授权信息(roleId)
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		String url = ((FilterInvocation) object).getRequestUrl();		// object 是一个URL，被用户请求的url。
		if(url == null) {
			return null;
		}
		
		/*
		 * 去掉url的参数
		 */
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(1, firstQuestionMarkIndex);
		} else {
			url = url.substring(1);	
		}

		Iterator<String> ite = fixedResourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();		
			if (url.equals(resURL)) {
				return fixedResourceMap.get(resURL);
			}
		}

		return null;
	}

	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		//TODO ????
		Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
		returnCollection.add(new SecurityConfig("1"));
		returnCollection.add(new SecurityConfig("2"));
		returnCollection.add(new SecurityConfig("3"));
		returnCollection.add(new SecurityConfig("4"));
		return returnCollection;
	}


	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Map<String, Collection<ConfigAttribute>> getFixedResourceMap() {
		if(fixedResourceMap == null) {
			loadOnStartup1();
		}
		return fixedResourceMap;
	}

	public void setFixedResourceMap(
			Map<String, Collection<ConfigAttribute>> fixedResourceMap) {
		this.fixedResourceMap = fixedResourceMap;
	}

	public Map<Integer, Map<Integer, RolePurviewDyn>> getDynamicResourceMap() {
		if(dynamicResourceMap == null) {
			loadOnStartup2();
		}
		return dynamicResourceMap;
	}

	public void setDynamicResourceMap(
			Map<Integer, Map<Integer, RolePurviewDyn>> dynamicResourceMap) {
		this.dynamicResourceMap = dynamicResourceMap;
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
		
	}

}
