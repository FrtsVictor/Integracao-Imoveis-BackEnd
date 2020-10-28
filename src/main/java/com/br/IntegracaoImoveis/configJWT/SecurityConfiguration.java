package com.br.IntegracaoImoveis.configJWT;


import static com.br.IntegracaoImoveis.configJWT.SecurityConstants.SIGN_UP_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import com.br.IntegracaoImoveis.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
		.and().csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
      .antMatchers("/*/users/**").hasRole("USER")
      .antMatchers("/*/imoveis/**").hasRole("USER")
      .antMatchers("/*/admin/**").hasRole("ADMIN")
      .and()
      .addFilter(new JWTAuthenticationFilter(authenticationManager()))
      .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
	

}
