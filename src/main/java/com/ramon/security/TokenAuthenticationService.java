package com.ramon.security;


import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ramon.DAO.UsuarioDAO;
import com.ramon.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	// EXPIRATION_TIME = 10 dias
		static final long EXPIRATION_TIME = 860_000_000;
		//public final long EXPIRATION_TIME = 600_000;
		public final String SECRET = "kverrna";
		public final String TOKEN_PREFIX = "vitoria";
		public final String HEADER_STRING = "Authorization";
		
		public TokenAuthenticationService() {}
		
		public  void addAuthentication(HttpServletResponse response, String username) {
			String JWT = Jwts.builder()
					.setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECRET)
					.compact();
			
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		}
		
		public  Authentication getAuthentication(HttpServletRequest request,UsuarioDAO usuarioDao) 
		{
			Autorizacao auth = new Autorizacao();
			auth.setId(2L);
			auth.setNome("USER");
			String token = request.getHeader(HEADER_STRING);
			ArrayList<Autorizacao> autorizacoes = new ArrayList<>();
			
			
			if (token != null) {
				// faz parse do token
				String user = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
						.getBody()
						.getSubject();
				
				if (user != null) 
				{
					Usuario usuario=null;
					try
					{
						usuario = usuarioDao.findByUsername(user);
						usuario.getPassword();
						
					}catch (Exception e) {
						
					}
					
					
					return new UsernamePasswordAuthenticationToken(user, null, autorizacoes);
				}
			}
			return null;
		}
		
}

