package com.br.IntegracaoImoveis.DTO;



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
    
	
	public ImovelDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdImobile() {
		return idImobile;
	}
	public void setIdImobile(String idImobile) {
		this.idImobile = idImobile;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTransacao() {
		return transacao;
	}
	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}
	public Integer getDormitorios() {
		return dormitorios;
	}
	public void setDormitorios(Integer dormitorios) {
		this.dormitorios = dormitorios;
	}
	public Integer getBanheiros() {
		return banheiros;
	}
	public void setBanheiros(Integer banheiros) {
		this.banheiros = banheiros;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getAreaUtil() {
		return areaUtil;
	}
	public void setAreaUtil(Double areaUtil) {
		this.areaUtil = areaUtil;
	}
	public Double getValorCondominio() {
		return valorCondominio;
	}
	public void setValorCondominio(Double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public String getUrlImovel() {
		return urlImovel;
	}
	public void setUrlImovel(String urlImovel) {
		this.urlImovel = urlImovel;
	}
	public String getUrlYouTube() {
		return urlYouTube;
	}
	public void setUrlYouTube(String urlYouTube) {
		this.urlYouTube = urlYouTube;
	}
	public boolean isDestaque() {
		return destaque;
	}
	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

    

}
