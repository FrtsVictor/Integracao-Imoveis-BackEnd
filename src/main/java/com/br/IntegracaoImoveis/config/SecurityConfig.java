//package com.br.IntegracaoImoveis.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("{noop}admin").roles("ADMIN")
//		.and()
//		.withUser("vct").password("{noop}123321").roles("ADMIN")
//		;
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.anyRequest().authenticated()
//		.and()
//		.httpBasic()
//		.and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		;
//	}
//
////	public PasswordEncoder passwordEncoder() {
////		return NoOpPasswordEncoder.getInstance();
////	}
//	
//	
//}