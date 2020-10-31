package com.br.IntegracaoImoveis.exceptions;

public class ImovelAlreadyLikedException extends RuntimeException {

	private static final long serialVersionUID = 4097220015877199455L;
	
	String msg;
	Long id;
	
	public ImovelAlreadyLikedException(String msg, Long id) {
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
	 
	
}
