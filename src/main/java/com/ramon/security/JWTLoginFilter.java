package com.ramon.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramon.DAO.UsuarioDAO;
import com.ramon.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private UsuarioDAO usuarioDao;
	private Usuario usuario;
	private AccountCredentials credentials=null;
	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		credentials = new ObjectMapper().readValue(request.getInputStream(),AccountCredentials.class);
		
		if(usuarioDao==null)
		{
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			usuarioDao = webContext.getBean(UsuarioDAO.class);
		}
		usuario = usuarioDao.findByUsername(credentials.getUserName());
		
		return getAuthenticationManager().authenticate(
				(Authentication) new UsernamePasswordAuthenticationToken(
						credentials.getUserName(), 
						credentials.getPassword(), 
						usuario.getAuthorities()
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		response.addHeader("Token",JwtUtils.generateToken(usuario));
	}
	
	
}
