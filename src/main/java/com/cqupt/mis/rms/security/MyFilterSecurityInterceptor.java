package com.cqupt.mis.rms.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/**
 * 该过滤器用于在用户请求某一资源时进行拦截，开始进行权限检查
 * 
 * @author welkin
 *
 */
@Deprecated
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{
	{
		System.out.println("***********************3********************************");
	}
	@Resource
	private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
		
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException{
		
		InterceptorStatusToken  token = super.beforeInvocation(fi);
		
		try{
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		}finally{
			super.afterInvocation(token, null);
		}

	}


	public void destroy() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.filterInvocationSecurityMetadataSource;
	}
	
}
