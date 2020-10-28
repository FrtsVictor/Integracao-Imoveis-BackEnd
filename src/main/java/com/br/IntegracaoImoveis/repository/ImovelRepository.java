package com.br.IntegracaoImoveis.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.Imovel;

@Repository
public interface ImovelRepository extends PagingAndSortingRepository<Imovel, Long> {
	
	@Query(value = "FROM Imovel WHERE idUsuario = ?1")
	Imovel imovelById(Long idUsuario);
	
	Imovel findByIdImobile(String idImobile);
	
	Set<Imovel> findAll();
	
}
