package com.br.IntegracaoImoveis.model;

import javax.annotation.Generated;

public class AuthenticatedUser {

	private String username;
	private String authenticationType = "Bearer";
	private String token;
	private String message;
	private String role;
	private String expiratation;

	@Generated("SparkTools")
	private AuthenticatedUser(Builder builder) {
		this.username = builder.username;
		this.authenticationType = builder.authenticationType;
		this.token = builder.token;
		this.message = builder.message;
		this.role = builder.role;
		this.expiratation = builder.Expiratation;
	}

	@Override
	public String toString() {
		return 
				"MSG: " + message + "\n" + 
				"USERNAME: " + username + "\n" +
				"AUTH: " + authenticationType + "\n" + 
				"ROLE: " + role + "\n" + 
				"TOKEN: " + token + "\n" + 
				"EXPIRATION: " + expiratation;

	}


	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String username;
		private String authenticationType;
		private String token;
		private String message;
		private String role;
		private String Expiratation;

		private Builder() {
		}

		public Builder Username(String username) {
			this.username = username;
			return this;
		}

		public Builder AuthenticationType(String authenticationType) {
			this.authenticationType = authenticationType;
			return this;
		}

		public Builder Token(String token) {
			this.token = token;
			return this;
		}

		public Builder Message(String message) {
			this.message = message;
			return this;
		}

		public Builder Role(String role) {
			this.role = role;
			return this;
		}

		public Builder Expiratation(String Expiratation) {
			this.Expiratation = Expiratation;
			return this;
		}

		public AuthenticatedUser build() {
			return new AuthenticatedUser(this);
		}
	}

}
