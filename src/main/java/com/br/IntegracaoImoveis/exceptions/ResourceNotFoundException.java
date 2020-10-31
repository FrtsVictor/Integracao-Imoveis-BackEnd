package com.br.IntegracaoImoveis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 4097220015877199455L;
	String msg;

	public ResourceNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	
	
}
