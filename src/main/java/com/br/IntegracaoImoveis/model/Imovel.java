package com.br.IntegracaoImoveis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "imoveis")
@EntityListeners(AuditingEntityListener.class)
public class Imovel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column()
	private boolean favorito;
	
	@Column
	private Integer codigoInterno;
	
    @Column
    private String codigoAnuncio;
	
    @Column
    private String idTipo;
	
    @Column
    private String tipo;
	
    @Column
    private String tipoUrl;
	
    @Column
    private String endereco;
	
    @Column
    private String idCidade;
	
    @Column
    private String cidade;
	
    @Column
    private String cidadeUrl;
	
    @Column
    private Integer codigoUF;
	
    @Column
    private String idBairro;
	
    @Column
    private String bairro;
	
    @Column
    private String bairroUrl;
	
    @Column
    private String cep;
	
    @Column
    private String transacao;
	
    @Column
    private Integer dormitorios;
	
    @Column
    private Integer banheiros;
	
    @Column
    private Double valor;
	
    @Column
    private Double areaUtil;
	
    @Column
    private Double valorIPTU;
	
    @Column
    private Double valorCondominio;
	
    @Column
    private String observacoes;
	
    @Column
    private String descricao;
	
    @Column
    private String idProprietario;
	
    @Column
    private String nomeProprietario;
	
    @Column
    private String idCorretor;
	
    @Column
    private Integer idSerialCorretor;
	
    @Column
    private String nomeCorretor;
	
    @Column
    private String urlImagem;
	
    @Column
    private String urlImovel;
	
    @Column
    private String urlYouTube;
	
    @Column
    private boolean exibeEndereco;
	
    @Column
    private boolean visivel; 
	
    @Column
    private boolean destaque;
	
    @Column
    private boolean visivelComunidadeAlterdata;
	
    @Column
    private boolean simulaFinanciamento;
	
    @Column
    private Integer numeroIPTU;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(Integer codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getCodigoAnuncio() {
		return codigoAnuncio;
	}

	public void setCodigoAnuncio(String codigoAnuncio) {
		this.codigoAnuncio = codigoAnuncio;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoUrl() {
		return tipoUrl;
	}

	public void setTipoUrl(String tipoUrl) {
		this.tipoUrl = tipoUrl;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(String idCidade) {
		this.idCidade = idCidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidadeUrl() {
		return cidadeUrl;
	}

	public void setCidadeUrl(String cidadeUrl) {
		this.cidadeUrl = cidadeUrl;
	}

	public Integer getCodigoUF() {
		return codigoUF;
	}

	public void setCodigoUF(Integer codigoUF) {
		this.codigoUF = codigoUF;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairroUrl() {
		return bairroUrl;
	}

	public void setBairroUrl(String bairroUrl) {
		this.bairroUrl = bairroUrl;
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

	public Double getValorIPTU() {
		return valorIPTU;
	}

	public void setValorIPTU(Double valorIPTU) {
		this.valorIPTU = valorIPTU;
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

	public String getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(String idProprietario) {
		this.idProprietario = idProprietario;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getIdCorretor() {
		return idCorretor;
	}

	public void setIdCorretor(String idCorretor) {
		this.idCorretor = idCorretor;
	}

	public Integer getIdSerialCorretor() {
		return idSerialCorretor;
	}

	public void setIdSerialCorretor(Integer idSerialCorretor) {
		this.idSerialCorretor = idSerialCorretor;
	}

	public String getNomeCorretor() {
		return nomeCorretor;
	}

	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
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

	public boolean isExibeEndereco() {
		return exibeEndereco;
	}

	public void setExibeEndereco(boolean exibeEndereco) {
		this.exibeEndereco = exibeEndereco;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public boolean isDestaque() {
		return destaque;
	}

	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

	public boolean isVisivelComunidadeAlterdata() {
		return visivelComunidadeAlterdata;
	}

	public void setVisivelComunidadeAlterdata(boolean visivelComunidadeAlterdata) {
		this.visivelComunidadeAlterdata = visivelComunidadeAlterdata;
	}

	public boolean isSimulaFinanciamento() {
		return simulaFinanciamento;
	}

	public void setSimulaFinanciamento(boolean simulaFinanciamento) {
		this.simulaFinanciamento = simulaFinanciamento;
	}

	public Integer getNumeroIPTU() {
		return numeroIPTU;
	}

	public void setNumeroIPTU(Integer numeroIPTU) {
		this.numeroIPTU = numeroIPTU;
	}
	
	
	
}
