package com.br.IntegracaoImoveis.config;

import static com.br.IntegracaoImoveis.config.SecurityConstants.EXPIRATION_TIME;
import static com.br.IntegracaoImoveis.config.SecurityConstants.HEADER_STRING;
import static com.br.IntegracaoImoveis.config.SecurityConstants.SECRET;
import static com.br.IntegracaoImoveis.config.SecurityConstants.TOKEN_PREFIX;
import com.br.IntegracaoImoveis.model.AuthenticatedUser;

import java.io.IOException;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.br.IntegracaoImoveis.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter { 

	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@Override 
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
			try {
				User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
				return this.authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			} catch (IOException e) {	
				throw new RuntimeException(e);
			}
	}

	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult
											) throws IOException, ServletException {
		
		String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();		
		
		String token = Jwts
				.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();		
		
		AuthenticatedUser authUser = AuthenticatedUser.builder()
				.Message("Bem vindo a IntegracaoImoveis Rest API! :)")
				.Username(username)
				.AuthenticationType("Bearer")
				.Token(token)
				.Expiratation("24hrs")
				.Role(authResult.getAuthorities().toString())
				.build();
		
		response.getWriter().write(authUser.toString());
		
		String bearerToken = TOKEN_PREFIX + token;
		response.addHeader(HEADER_STRING, bearerToken);
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
