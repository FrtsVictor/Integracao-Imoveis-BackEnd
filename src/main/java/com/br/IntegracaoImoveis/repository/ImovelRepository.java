package com.br.IntegracaoImoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.IntegracaoImoveis.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {

}
