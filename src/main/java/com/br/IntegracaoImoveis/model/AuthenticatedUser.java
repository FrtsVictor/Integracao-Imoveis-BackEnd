package com.br.IntegracaoImoveis.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AuthenticatedUser {

	private String username;
	private String authenticationType;
	private String token;
	private String message;
	private String role;
	private String expiratation;


}
