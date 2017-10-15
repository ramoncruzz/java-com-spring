package com.ramon.teste.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

//FILTRAR TODOS OS ACESSOS

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		Authentication authentication = TokenAuthenticationService
				.getAuthentication((HttpServletRequest) request);
		
//		String permissao = ((Roles)authentication.getAuthorities().toArray()[0]).getAuthority();
//		System.out.println("=== "+permissao);
//		System.out.println("--> "+((HttpServletRequest)request).getRequestURI());
//		
//		if(permissao.equalsIgnoreCase("USER"))	
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		else 
//			SecurityContextHolder.getContext().setAuthentication(null);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}