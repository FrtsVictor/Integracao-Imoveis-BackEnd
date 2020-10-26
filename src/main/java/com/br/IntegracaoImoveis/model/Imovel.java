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




@Entity
@Table(name = "imoveis"
, uniqueConstraints = {
		@UniqueConstraint(columnNames = "id", name = "ImovelID_Already_Exists") })
//		@UniqueConstraint(columnNames = "email", name = "Email_Already_Exists") })
@EntityListeners(AuditingEntityListener.class)
public class Imovel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_table;
	
	@Column(unique = true)
	private String id;

	private Long idUsuario;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "imoveis")
	private Set<User> usuarios = new HashSet<>();
 
	private boolean favorito;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCodigoInterno() {
		return codigoInterno;
	}

	public Long getId_table() {
		return id_table;
	}

	public void setId_table(Long id_table) {
		this.id_table = id_table;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_table == null) ? 0 : id_table.hashCode());
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
		if (id_table == null) {
			if (other.id_table != null)
				return false;
		} else if (!id_table.equals(other.id_table))
			return false;
		return true;
	}
	
	
	
}
