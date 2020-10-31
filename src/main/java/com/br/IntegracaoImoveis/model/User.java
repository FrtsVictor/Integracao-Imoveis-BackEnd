package com.br.IntegracaoImoveis.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "usuarios"
, uniqueConstraints = {
		@UniqueConstraint(columnNames = "username", name = "UserName_Already_Exists"),
		@UniqueConstraint(columnNames = "email", name = "Email_Already_Exists") })
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@Size(min= 3 , max = 50)
	@NotBlank
	private String firstName;

	@Size(min = 3, max = 50)
	@NotBlank
	private String lastName;

	@Size(max = 80)
	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(min = 5)
	private String password;
	
	@NotBlank
	@Size(min = 5, max = 20)
	private String username;
	
	private boolean admin;
	

	@ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name="imoveis_favoritos",
    joinColumns= { @JoinColumn(name="usuarios_id") },
    inverseJoinColumns = { @JoinColumn(name="imoveis_id") })
	private Set<Imovel> imoveis = new HashSet<>();
	


	public void addImovel(Imovel imovel) {
		this.imoveis.add(imovel);
		imovel.getUsuarios().add(this);
	}
	
	
	public void removeImovel(Imovel imovel) {
		this.imoveis.remove(imovel);
		imovel.getUsuarios().remove(this);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	


	

}
