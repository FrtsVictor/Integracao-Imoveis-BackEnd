package com.br.IntegracaoImoveis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties({ "usuarios" })
@Entity
@Table(name = "imoveis"
, uniqueConstraints = {
		@UniqueConstraint(columnNames = "id", name = "ImovelID_Already_Exists") })
//		@UniqueConstraint(columnNames = "email", name = "Email_Already_Exists") })
@EntityListeners(AuditingEntityListener.class)
public class Imovel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String idImobile;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "imoveis")
	private Set<User> usuarios = new HashSet<>();
	
	private Integer codigoInterno;
    private String codigoAnuncio;
    private String idTipo;
    private String tipo;
    private String tipoUrl;
    private String endereco;
    private String idCidade;
    private String cidade;
    private String cidadeUrl;
    private Integer codigoUF;
    private String idBairro;
    private String bairro;
    private String bairroUrl;
    private String cep;
    private String transacao;
    private Integer dormitorios;
    private Integer banheiros;
    private Double valor;
    private Double areaUtil;
    private Double valorIPTU;
    private Double valorCondominio;
    private String observacoes;
    private String descricao;
    private String idProprietario;
    private String nomeProprietario;	
    private String idCorretor;
    private Integer idSerialCorretor;
    private String nomeCorretor;
    private String urlImagem;
    private String urlImovel;
    private String urlYouTube;
    private boolean exibeEndereco;
    private boolean visivel; 
    private boolean destaque;
    private boolean visivelComunidadeAlterdata;
    private boolean simulaFinanciamento;
    private Integer numeroIPTU;


	public void addUser(User user) {
		this.usuarios.add(user);
		user.getImoveis().add(this);
	}
	
	
	public void removeUser(User user) {
		this.usuarios.remove(user);
		user.getImoveis().remove(this);
	}

    

	public Imovel() {
		super();
	}


	public Imovel(String idImobile, Set<User> usuarios, Integer codigoInterno, String codigoAnuncio, String idTipo,
			String tipo, String tipoUrl, String endereco, String idCidade, String cidade, String cidadeUrl,
			Integer codigoUF, String idBairro, String bairro, String bairroUrl, String cep, String transacao,
			Integer dormitorios, Integer banheiros, Double valor, Double areaUtil, Double valorIPTU,
			Double valorCondominio, String observacoes, String descricao, String idProprietario,
			String nomeProprietario, String idCorretor, Integer idSerialCorretor, String nomeCorretor, String urlImagem,
			String urlImovel, String urlYouTube, boolean exibeEndereco, boolean visivel, boolean destaque,
			boolean visivelComunidadeAlterdata, boolean simulaFinanciamento, Integer numeroIPTU) {
		super();
		this.idImobile = idImobile;
		this.usuarios = usuarios;
		this.codigoInterno = codigoInterno;
		this.codigoAnuncio = codigoAnuncio;
		this.idTipo = idTipo;
		this.tipo = tipo;
		this.tipoUrl = tipoUrl;
		this.endereco = endereco;
		this.idCidade = idCidade;
		this.cidade = cidade;
		this.cidadeUrl = cidadeUrl;
		this.codigoUF = codigoUF;
		this.idBairro = idBairro;
		this.bairro = bairro;
		this.bairroUrl = bairroUrl;
		this.cep = cep;
		this.transacao = transacao;
		this.dormitorios = dormitorios;
		this.banheiros = banheiros;
		this.valor = valor;
		this.areaUtil = areaUtil;
		this.valorIPTU = valorIPTU;
		this.valorCondominio = valorCondominio;
		this.observacoes = observacoes;
		this.descricao = descricao;
		this.idProprietario = idProprietario;
		this.nomeProprietario = nomeProprietario;
		this.idCorretor = idCorretor;
		this.idSerialCorretor = idSerialCorretor;
		this.nomeCorretor = nomeCorretor;
		this.urlImagem = urlImagem;
		this.urlImovel = urlImovel;
		this.urlYouTube = urlYouTube;
		this.exibeEndereco = exibeEndereco;
		this.visivel = visivel;
		this.destaque = destaque;
		this.visivelComunidadeAlterdata = visivelComunidadeAlterdata;
		this.simulaFinanciamento = simulaFinanciamento;
		this.numeroIPTU = numeroIPTU;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idImobile == null) ? 0 : idImobile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imovel other = (Imovel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idImobile == null) {
			if (other.idImobile != null)
				return false;
		} else if (!idImobile.equals(other.idImobile))
			return false;
		return true;
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

	public Set<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
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
