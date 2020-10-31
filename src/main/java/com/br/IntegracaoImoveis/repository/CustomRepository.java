package com.br.IntegracaoImoveis.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.br.IntegracaoImoveis.model.Imovel;

@Service
@Component
public class CustomRepository {

	private final EntityManager em;

	public CustomRepository(EntityManager em) {
		this.em = em;
	}

	public List<Imovel> find(Long id, String cidade, String bairro, String tipo) {

		String query = "SELECT imv.* FROM imoveis_favoritos fav JOIN imoveis imv ON imv.id = fav.imoveis_id WHERE fav.usuarios_id = "
				+ id;
		String parameter = " AND";

		if (bairro != null) {
			query += parameter + " imv.bairro LIKE :bairro";
		}

		if (cidade != null) {
			query += parameter + " imv.cidade LIKE :cidade";
		}

		if (tipo != null) {
			query += parameter + " imv.tipo LIKE :tipo";
		}

		Query q = em.createNativeQuery(query, Imovel.class);

		if (bairro != null) {
			q.setParameter("bairro", "%" + bairro + "%");
		}

		if (cidade != null) {
			q.setParameter("cidade", "%" + cidade + "%");
		}

		if (tipo != null) {
			q.setParameter("tipo", "%" + tipo + "%");
		}

		List<Imovel> ab = q.getResultList();
		return ab;
	}

}
