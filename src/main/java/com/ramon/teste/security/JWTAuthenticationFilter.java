package com.ramon.teste.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.ramon.teste.DAO.UsuarioDAO;


//FILTRAR TODOS OS ACESSOS
public class JWTAuthenticationFilter extends GenericFilterBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private static String USER="USER";
	private static String ADMIN="ADMIN";
	private static String JEDI="JEDI";
	
	private static String URI_BEBIDA="/bebida";
	@Autowired
	private UsuarioDAO usuarioDao;  
	
    public JWTAuthenticationFilter() {}
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		if(usuarioDao==null)
		{
			ServletContext servletContext = request.getServletContext();//forca o carregamento de um bean 
			WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			usuarioDao = webAppContext.getBean(UsuarioDAO.class);
		}
		
		TokenAuthenticationService tks = new TokenAuthenticationService();
		Authentication authentication=tks.getAuthentication((HttpServletRequest) request,usuarioDao);
		
		SecurityContextHolder.getContext().setAuthentication(filtro(request, authentication));
		
		filterChain.doFilter(request, response);
	}
	private Authentication filtro(ServletRequest request,Authentication authentication)
	{	
		Authentication authToSend=null;
		try
		{
			
			String permissao = ((Autorizacao)authentication.getAuthorities().toArray()[0]).getAuthority();
			String metodo = ((HttpServletRequest)request).getMethod()+"";
			String caminho =((HttpServletRequest)request).getRequestURI()+"";
			
			System.out.println("=== "+permissao);
			System.out.println("-->"+metodo);
			System.out.println(caminho);
			
			if(permissao.trim().equals(JEDI))
			{
				authToSend=authentication;
			}
			
			if(permissao.trim().equals(USER))
			{
				if(metodo.trim().equals("GET"))
				{
					if(caminho.trim().equals(URI_BEBIDA))
					{
						authToSend=authentication;
					}
				}
				
			}
			
		}catch (Exception e) {
			
		}

		
		return authToSend;
	}

}