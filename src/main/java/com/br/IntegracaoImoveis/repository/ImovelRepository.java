package com.br.IntegracaoImoveis.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.Imovel;

@Repository
public interface ImovelRepository extends PagingAndSortingRepository<Imovel, Long> {

}
