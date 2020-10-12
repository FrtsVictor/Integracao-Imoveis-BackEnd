package com.br.IntegracaoImoveis.exceptions;

public class InvalidQueryException extends Exception {
	
	private static final long serialVersionUID = 3564270935211966625L;
	
	String msg;
	Long id;
	
	public InvalidQueryException(String msg, Long id) {
		super();
		this.msg = msg;
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
