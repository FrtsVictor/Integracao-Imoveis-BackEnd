package com.br.IntegracaoImoveis.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.IntegracaoImoveis.model.Imovel;

@Repository
public interface ImovelRepository extends PagingAndSortingRepository<Imovel, Long> {

	Imovel findByIdImobile(String idImobile);

	Set<Imovel> findAll();

	@Query(value = "select imv.* from imoveis_favoritos fav join imoveis imv on imv.id = fav.imoveis_id where fav.usuarios_id = :id ", nativeQuery = true)
	Page<Imovel> allByUser(@Param("id") Long id, Pageable pageable);

	@Query(value = "SELECT imv.bairro, imv.cidade FROM imoveis_favoritos fav JOIN imoveis imv ON imv.id = fav.imoveis_id WHERE fav.usuarios_id = :id ", nativeQuery = true)
	Set<?> findAllCidadeBairro(@Param("id") Long id);

}
