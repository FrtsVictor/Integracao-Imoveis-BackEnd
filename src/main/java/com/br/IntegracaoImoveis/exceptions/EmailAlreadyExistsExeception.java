package com.br.IntegracaoImoveis.exceptions;

public class EmailAlreadyExistsExeception extends Exception {

	private static final long serialVersionUID = 4738052029702073157L;
	
	String msg;
	String email;
		
	
	public EmailAlreadyExistsExeception(String msg, String email) {
		super();
		this.msg = msg;
		this.email = email;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	
}
