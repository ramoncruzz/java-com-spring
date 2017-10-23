package com.ramon.teste.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	@SuppressWarnings("unused")
	private static String URI_BEBIDA="/bebida";
	@SuppressWarnings("unused")
	private static String URI_ALIMENTO="/alimento";
	private static String URI_CARDAPIO="/cardapio";
	private static String URI_AVALIACAO="/avaliacao";
	private static String URI_REGIAO="/regiao";
	private static String URI_ENDERECO="/endereco";
	private static String URI_USUARIO="/usuario";
	private static String URI_PEDIDO="pedido";
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
			
			if(permissao.trim().equals(JEDI) ||permissao.trim().equals(ADMIN))
			{
				authToSend=authentication;
			}
			
			if(permissao.trim().equals(USER))
			{
				if(metodo.trim().equals("GET"))
				{
					if(checkPath(caminho,URI_REGIAO)||checkPath(caminho, URI_CARDAPIO)||checkPath(caminho, URI_PEDIDO))
					{
						authToSend=authentication;
					}
				}
				if(metodo.trim().equals("POST"))
				{
					if(checkPath(caminho, URI_USUARIO)||checkPath(caminho, URI_ENDERECO)||checkPath(caminho, URI_AVALIACAO)||checkPath(caminho, URI_PEDIDO))
					{
						authToSend=authentication;
					}
				}
				if(metodo.trim().equals("PUT"))
				{
					if(checkPath(caminho, URI_USUARIO)||checkPath(caminho, URI_ENDERECO)||checkPath(caminho, URI_PEDIDO))
					{
						authToSend=authentication;
					}
				}
				if(metodo.trim().equals("DELETE"))
				{
					if(checkPath(caminho, URI_USUARIO)||checkPath(caminho, URI_ENDERECO)||checkPath(caminho, URI_PEDIDO))
					{
						authToSend=authentication;
					}
				}
			}
			
		}catch (Exception e) {
			
		}

		
		return authToSend;
	}
	private boolean checkPath(String url , String pattern)
	{
	      Pattern r = Pattern.compile(pattern);
	      Matcher m = r.matcher(url);
	  
		return m.find();
	}

}