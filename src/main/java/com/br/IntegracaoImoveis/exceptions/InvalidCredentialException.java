package com.br.IntegracaoImoveis.exceptions;

public class InvalidCredentialException extends Exception {

	String msg;
	
	private static final long serialVersionUID = 6313929789302912603L;

	public InvalidCredentialException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
