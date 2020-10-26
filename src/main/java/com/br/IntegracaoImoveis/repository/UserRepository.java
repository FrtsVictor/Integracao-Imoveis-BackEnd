package com.br.IntegracaoImoveis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.User;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@Query(value = "FROM User WHERE email = ?1")
		User userByEmail(String email);

	@Query(value = "FROM User WHERE id = ?1")
	User userById(Long id);

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

	@Query(value = "FROM User WHERE username = ?1")
	User userByUsername(String username);
	
		
	
}
