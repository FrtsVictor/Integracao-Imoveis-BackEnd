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

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor
@JsonIgnoreProperties({ "usuarios" })
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


	@Override
	public String toString() {
		return bairro;
	}

	
	
}
