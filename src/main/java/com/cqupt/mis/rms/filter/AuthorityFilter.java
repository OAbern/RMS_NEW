package com.cqupt.mis.rms.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.impl.AuthorityResource;

/**
 * 进行权限验证的拦截器
 * @author Bern
 *
 */
public class AuthorityFilter implements Filter {
	
	private AuthorityResource authorityResource;
	
	private WebApplicationContext wac;
	
	private Map<String, List<Integer>> fixedResourceMap;	//资源URL为key， 对应的角色id为value
	
	private Map<Integer, Map<Integer, RolePurviewDyn>> dynamicResourceMap;	//外层map中：类别id为key， 权限为value；内层map中：角色id为key，权限为value

	private Set<String> noInterceptUrl;		//不进行拦截的url列表
	/**
	 * 初始化的方法
	 */
	public void init(FilterConfig config) throws ServletException {
		wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		authorityResource = (AuthorityResource) wac.getBean("authorityResource");
		
		fixedResourceMap = authorityResource.getFixedResourceMap();
		dynamicResourceMap = authorityResource.getDynamicResourceMap();
		
		noInterceptUrl = new HashSet<String>();
		noInterceptUrl.add("login");
		noInterceptUrl.add("error");
		noInterceptUrl.add("");
	}
	
	/**
	 * 销毁方法
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestUrl = request.getRequestURI();
		int index = requestUrl.indexOf("rms2/");
		requestUrl = requestUrl.substring(index+5);
		System.out.println(requestUrl);
		
		if(noIntercept(requestUrl)) {	//不拦截的页面，通过
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if(decideFixed(request)) {	//静态权限匹配，通过
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if(decideDynamic(request)) {	//动态权限匹配，通过
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		//权限校验不通过,转发到到越权界面
		request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
//		response.sendRedirect("accessDenied.jsp");
		
	}
	
	/**
	 * 检测是否满足静态资源的权限
	 * @param request
	 * @return
	 */
	public boolean decideFixed(HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		
		if(fixedResourceMap.containsKey(requestUrl)) {
			int roleId = (Integer) request.getAttribute("roleId");//TODO ????
			List<Integer> list = fixedResourceMap.get(requestUrl);
			if(list.contains(roleId)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 检测是否为不进行拦截的URL
	 * @param url
	 * @return
	 */
	public boolean noIntercept(String url) {
		if(noInterceptUrl.contains(url))
			return true;
		
		return false;
			
	}
	
	/**
	 * 检测是否满足动态资源的权限
	 */
	public boolean decideDynamic(HttpServletRequest request) {
		//TODO
		
		return false;
	}
	
}
