package com.br.IntegracaoImoveis.repository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.br.IntegracaoImoveis.DTO.ImovelDTO;
import com.br.IntegracaoImoveis.model.Imovel;

@Service
public interface ImovelMapper {

	default ImovelDTO imovelToDTO(Imovel entity) {
		ImovelDTO dto = new ImovelDTO();

		dto.setAreaUtil(entity.getAreaUtil());
		dto.setBairro(entity.getBairro());
		dto.setBanheiros(entity.getBanheiros());
		dto.setCep(entity.getCep());
		dto.setCidade(entity.getCidade());
		dto.setDescricao(entity.getDescricao());
		dto.setDestaque(entity.isDestaque());
		dto.setDormitorios(entity.getDormitorios());
		dto.setEndereco(entity.getEndereco());
		dto.setId(entity.getId());
		dto.setIdImobile(entity.getIdImobile());
		dto.setObservacoes(entity.getObservacoes());
		dto.setTipo(entity.getTipo());
		dto.setTransacao(entity.getTransacao());
		dto.setUrlImagem(entity.getUrlImagem());
		dto.setUrlImovel(entity.getUrlImovel());
		dto.setUrlYouTube(entity.getUrlYouTube());
		dto.setValor(entity.getValor());
		dto.setValorCondominio(entity.getValorCondominio());
		return dto;
	}

	default Page<ImovelDTO> userToUserDTOs(Page<Imovel> users) {
		return users.map(this::imovelToDTO);
	}

}
