package com.candangas.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.usuario-query}")
	private String usuarioQuery;
	
	@Value("${spring.queries.autorizacao-query}")
	private String autorizacaoQuery;
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET,"/v0").hasAuthority("ADMIN")
			//.antMatchers(HttpMethod.GET,"/v0/**").permitAll()
			//.antMatchers(HttpMethod.POST,"/v0/**").permitAll()
			//.antMatchers(HttpMethod.PUT,"/v0/**").permitAll()
		//	.antMatchers(HttpMethod.DELETE,"/v0/**").permitAll()
//			.antMatchers(HttpMethod.GET,"/v0/firebaseNotifications").hasAuthority("USER")
//			.antMatchers(HttpMethod.POST,"/v0/firebaseNotifications").hasAuthority("USER")
//			.antMatchers(HttpMethod.GET,"/v0/sms").hasAuthority("USER")
			
			//USUARIO
			.antMatchers(HttpMethod.GET,"/v0/usuario").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/usuario/").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/usuario/busca/*").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/usuario/consulta").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/usuario/vendedores").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET,"/v0/usuario/administradores").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.POST,"/v0/usuario").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/usuario/").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/usuario/admin").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/usuario/ativar").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.POST,"/v0/usuario/desativar").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.POST,"/v0/usuario/*").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT,"/v0/usuario").hasAnyAuthority("USER","ADMIN")
			.antMatchers(HttpMethod.PUT,"/v0/usuario/recuperar").permitAll()
			.antMatchers(HttpMethod.DELETE,"/v0/usuario/**").hasAuthority("ADMIN")
            
			//PRODUTOS
			.antMatchers(HttpMethod.GET,"/v0/produto").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/produto/**").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/produto").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT,"/v0/produto").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/v0/produto").hasAuthority("ADMIN")
			
			//CATALOGO
			.antMatchers(HttpMethod.GET,"/v0/catalogo").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/catalogo/**").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/catalogo/").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT,"/v0/catalogo/").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/v0/catalogo/").hasAuthority("ADMIN")
			
			//ARQUIVO
			.antMatchers(HttpMethod.GET,"/v0/arquivo").hasAnyAuthority("USER","ADMIN")
			.antMatchers(HttpMethod.POST,"/v0/arquivo").permitAll()
			
			//ENDERECO 
			.antMatchers(HttpMethod.GET,"/v0/endereco").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/endereco/**").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/endereco").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/endereco/**").permitAll()
			.antMatchers(HttpMethod.PUT,"/v0/endereco").hasAnyAuthority("USER","ADMIN")
			.antMatchers(HttpMethod.DELETE,"/v0/endereco").hasAnyAuthority("USER","ADMIN")
			
            //SMS
			.antMatchers(HttpMethod.POST,"/v0/sms/gerar-codigo").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/sms/validar").permitAll()
            
			.anyRequest().authenticated()
			.and()
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/v0/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JwtAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
		  	  
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication()
		.usersByUsernameQuery(usuarioQuery)
		.authoritiesByUsernameQuery(autorizacaoQuery)
		.dataSource(dataSource)
		.passwordEncoder(bCrypt);
	}
}
