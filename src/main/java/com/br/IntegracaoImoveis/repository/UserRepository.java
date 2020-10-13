package com.br.IntegracaoImoveis.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.IntegracaoImoveis.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "FROM User WHERE email = ?1")
		User userByEmail(String email);

	@Query(value = "FROM User WHERE id = ?1")
	User userById(Long id);

	
}
