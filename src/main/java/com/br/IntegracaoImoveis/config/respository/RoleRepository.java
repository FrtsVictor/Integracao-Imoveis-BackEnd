package com.br.IntegracaoImoveis.config.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.ERole;
import com.br.IntegracaoImoveis.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}