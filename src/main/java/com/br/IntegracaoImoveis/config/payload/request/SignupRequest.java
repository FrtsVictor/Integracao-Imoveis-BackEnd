package com.br.IntegracaoImoveis.config.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    
	
	@NotBlank(message = "Name should be informed")
	private String firstName;
	
	@NotBlank(message = "lastName should be informed")
	private String lastName;
	
	@NotBlank
    private String username;
    
    @NotBlank
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    private String password;
    
    
  
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

	public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
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
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
