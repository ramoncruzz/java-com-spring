package com.ramon.security;

import java.io.IOException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramon.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

private static final String secretKey = "j4v4_s3cr3t";
public static String generateToken(Usuario usuario) throws JsonProcessingException{
	final Long hora = 1000L * 60L * 60L;
	Long horasValidade=720L;
	ObjectMapper mapper = new ObjectMapper();
	String userJson = mapper.writeValueAsString(usuario);
	Date agora = new Date();
	
	return Jwts.builder().claim("usr", userJson)
			.setIssuer("com.ramon")
			.setSubject(usuario.getUsername())
			//.setExpiration(new Date(agora.getTime()+(1000L * 60L)))
			.setExpiration(new Date(agora.getTime()+(hora*horasValidade)))
			.signWith(SignatureAlgorithm.HS256, secretKey).compact();

	}

	public static UserDetails parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
		UserDetails usuarioRetorno = null;
		try {
			String json = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("usr",
					String.class);
			JSONObject obj = new JSONObject(json);

			Autorizacao auth = new Autorizacao();
			JSONArray objeAuthArray = obj.getJSONArray("authorities");
			JSONObject objAuth = objeAuthArray.getJSONObject(0);
			auth.setId(objAuth.getLong("id"));
			auth.setNome(objAuth.getString("authority"));

			Usuario usuario = new Usuario();
			usuario.setId(obj.getLong("id"));
			usuario.setAutorizacao(auth);
			usuario.setAtivo(obj.getBoolean("ativo"));
			usuario.setNomeCompleto(obj.getString("nomeCompleto"));
			usuario.setPassword(obj.getString("password"));
			usuario.setUserName(obj.getString("username"));

			usuarioRetorno = usuario;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return usuarioRetorno;
	}

}
