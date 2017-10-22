package com.ramon.teste.security;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
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
			.antMatchers("/").permitAll()
			.antMatchers("/alimento").permitAll()
			.antMatchers("/usuario/**").permitAll()
			.antMatchers("/autorizacao/**").permitAll()
			.antMatchers(HttpMethod.POST,"/login").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
		  
		  
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
//		auth.inMemoryAuthentication()
//			.withUser("kverrna@gmail.com")
//			.password("123456")
//			.roles("USER");
		auth.jdbcAuthentication()
		.usersByUsernameQuery(usuarioQuery)
		.authoritiesByUsernameQuery(autorizacaoQuery)
		.dataSource(dataSource)
		.passwordEncoder(bCrypt);
	}
}
