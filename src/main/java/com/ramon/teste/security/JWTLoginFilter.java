package com.ramon.teste.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.management.relation.Role;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramon.teste.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private AccountCredentials credentials=null;
	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		 credentials = new ObjectMapper()
				.readValue(request.getInputStream(),AccountCredentials.class);
		
		ArrayList<Roles> autorizacoes = new ArrayList<>();
		autorizacoes.add(getAutorizacao(credentials));
		
		return getAuthenticationManager().authenticate(
				(Authentication) new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(), 
						autorizacoes
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		Roles role = getAutorizacao(credentials);
		if(role.getAuthority().equalsIgnoreCase(RoleNames.USER)) TokenAuthenticationService.addAuthentication(response, auth.getName());
		
	}
	private Usuario getAutorizacaoNoBanco(AccountCredentials credenciais)
	{
		return null;
	}
	private Roles getAutorizacao(AccountCredentials credenciais)
	{
		Roles role =null;
		if(!credenciais.equals(null))
		{
			if(!credenciais.getTokenFacebook().equals(null))
			{
				role=new Roles(RoleNames.USER);
			}
			if(!credenciais.getTokenGoogle().equals(null))
			{
				role=new Roles(RoleNames.USER);
			}
			
		}
		return role;
	}
}
