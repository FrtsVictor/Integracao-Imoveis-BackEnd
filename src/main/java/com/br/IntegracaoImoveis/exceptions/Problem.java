package com.br.IntegracaoImoveis.exceptions;


import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
@JsonInclude(Include.NON_NULL)
public class Problem {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;


	
	@Getter @Setter
	public static class Campo{
		private String nome;
		private String mensagem;
		
		
		
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}		
		
	}
	
	

}
