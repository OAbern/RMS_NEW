package com.cqupt.mis.rms.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 访问决策器类
 * 该类用于进行权限判断，判断某用户是否有权限访问某一资源
 * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源。
 * @author Bern
 *
 */
public class MyAccessDecisionManager implements AccessDecisionManager{
	{
		System.out.println("***********************2********************************");
	}
	/**
	 * 决定是否有访问权限
	 */
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		if( configAttributes == null ) {
			return ;
		}
		
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		
		while(ite.hasNext()) {
			
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			
			//ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
			for(GrantedAuthority ga: authentication.getAuthorities()) {		
				if(needRole.trim().equals(ga.getAuthority().trim())) {
					return ;
				}
			}
		}
		
		throw new AccessDeniedException("");
	}


	public boolean supports(ConfigAttribute attribute) {
		return true;
	}


	public boolean supports(Class<?> clazz) {
		return true;
	}

}
