package com.br.IntegracaoImoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.Imovel;


@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

}
