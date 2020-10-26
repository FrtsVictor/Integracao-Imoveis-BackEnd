package com.br.IntegracaoImoveis.repository;



import java.awt.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.Imovel;

@Repository
public interface ImovelRepository extends PagingAndSortingRepository<Imovel, Long> {
	
	

	@Query(value = "FROM Imovel WHERE idUsuario = ?1")
	List imovelByUsername(Long idUsuario);
	
	
}
