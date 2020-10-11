package com.br.IntegracaoImoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.IntegracaoImoveis.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
