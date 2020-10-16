package com.br.IntegracaoImoveis.config;

public class SecurityConstants {
	
	static final String SECRET = "IntegracaoImoveis";
	static final String TOKEN_PREFIX = "Bearer ";
	static final String HEADER_STRING = "Authorization";
	static final String SIGN_UP_URL = "/users/sign-up";
	static final Long EXPIRATION_TIME = 86400000l;
		
}
