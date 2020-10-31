package com.br.IntegracaoImoveis.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ImovelDTO {

	private Long id;
	private String idImobile;
    private String tipo;
    private String endereco;
    private String cidade;
    private String bairro;
    private String cep;
    private String transacao;
    private Integer dormitorios;
    private Integer banheiros;
    private Double valor;
    private Double areaUtil;
    private Double valorCondominio;
    private String observacoes;
    private String descricao;
    private String urlImagem;
    private String urlImovel;
    private String urlYouTube;
    private boolean destaque;
    
	    

}
