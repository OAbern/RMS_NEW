package com.cqupt.mis.rms.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.impl.AuthorityResource;

/**
 * 进行权限验证的拦截器
 * @author Bern
 *
 */
public class AuthorityFilter implements Filter {
	@Resource
	private AuthorityResource authorityResource;
	
	private Map<String, List<Integer>> fixedResourceMap;	//资源URL为key， 对应的角色id为value
	
	private Map<Integer, Map<Integer, RolePurviewDyn>> dynamicResourceMap;	//外层map中：类别id为key， 权限为value；内层map中：角色id为key，权限为value

	
	/**
	 * 初始化的方法
	 */
	public void init(FilterConfig arg0) throws ServletException {
		fixedResourceMap = authorityResource.getFixedResourceMap();
		dynamicResourceMap = authorityResource.getDynamicResourceMap();
	}
	
	/**
	 * 销毁方法
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(servletRequest, servletResponse);
	}

	public boolean decide() {
		
		return false;
		
	}
	
}
