package com.br.IntegracaoImoveis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "usuario", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@Column(nullable = false)
	@Size(min = 3, message = "Minimo 3 caracteres")
	@Size(max = 50, message = "Ultrapassa 50 caracteres")
	@NotBlank(message = "Vazio.")
	private String firstName;

	
	@Column(nullable = false)
	@Size(min = 3, message = "Minimo 3 caracteres")
	@Size(max = 50, message = "Ultrapassa 50 caracteres")
	@NotBlank(message = "Vazio.")
	private String lastName;

	
	@Column
	@Size(min = 5, message = "Minimo 5 caracteres")
	@Size(max = 50, message = "Ultrapassa 50 caracteres")
	@Email(message = "Invalido.")
	private String email;

	
	@Column
	@NotBlank( message = "Vazio.")
	@Size(min = 5, message = "Minimo 5 caractereres")
	private String password;

	@Column
	@NotBlank( message = "Vazio")
	@Size(min = 5, max = 20, message = "Minimo 5 caractereres" )
	private String username;

	private boolean admin;
	

	public boolean isAdmin() {
		return admin;
	}



	public void setAdmin(boolean admin) {
		this.admin = admin;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public User() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", username=" + username + ", roles=" +  "]";
	}
	
	
	

}
