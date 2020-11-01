package com.br.IntegracaoImoveis.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.DTO.ImovelDTO;
import com.br.IntegracaoImoveis.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@Query(value = "FROM User WHERE id = ?1")
	User userById(Long id);

	@Query(value = "FROM User WHERE username = ?1")
	Optional<User> userByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Set<User> findAll();

	Page<?> findAllById(Long id, Pageable pageable);

	Page<ImovelDTO> findAllByImoveis(Pageable pageable);

}
