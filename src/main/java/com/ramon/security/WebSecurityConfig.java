package com.ramon.security;

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
//			.antMatchers("/**").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/firebaseNotifications").hasAuthority("USER")
			.antMatchers(HttpMethod.POST,"/v0/firebaseNotifications").hasAuthority("USER")
			.antMatchers(HttpMethod.GET,"/v0/cardapio").hasAuthority("USER")
			.antMatchers(HttpMethod.GET,"/v0/pedidosMobile").hasAuthority("USER")
			.antMatchers(HttpMethod.GET,"/v0/sms").hasAuthority("USER")
			.antMatchers(HttpMethod.PUT,"/v0/usuario").hasAuthority("USER")
			.antMatchers(HttpMethod.GET,"/v0/usuario/**").hasAuthority("USER")
			.antMatchers(HttpMethod.POST,"/v0/usuario").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/usuario").permitAll()
			.antMatchers(HttpMethod.PUT,"/v0/usuario/recuperar").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/sms/gerar-codigo").permitAll()
			.antMatchers(HttpMethod.POST,"/v0/sms/validar").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/carregar").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/endereco/**").permitAll()
			.antMatchers(HttpMethod.GET,"/v0/pedidospool/**").hasAuthority("USER")
			.antMatchers(HttpMethod.POST,"/v0/pedidospool/**").hasAuthority("USER")
			.antMatchers(HttpMethod.PUT,"/v0/pedidospool/**").hasAuthority("USER")
//			.antMatchers(HttpMethod.GET,"/usuario").permitAll()
//			.antMatchers(HttpMethod.GET,"/autorizacao/popular").permitAll()
//			.antMatchers(HttpMethod.GET,"/pedidosMobile").hasAuthority("USER")
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
