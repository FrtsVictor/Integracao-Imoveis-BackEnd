package com.br.IntegracaoImoveis.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.br.IntegracaoImoveis.DTO.ImovelDTO;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.repository.UserRepository;

@Component
@Service
public class ImovelService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;

	public ImovelDTO toDTOClass(Imovel imovel) {
		return modelMapper.map(imovel, ImovelDTO.class);
	}

	public List<ImovelDTO> toDTOList(Set<Imovel> lista) {
		Set<ImovelDTO> dtoSet = new HashSet<>();
		for (Imovel imv : lista) {
			ImovelDTO imvs = toDTOClass(imv);
			dtoSet.add(imvs);
		}
		List<ImovelDTO> dtoList = new ArrayList<>(dtoSet);
		return dtoList;
	}

	public Page<ImovelDTO> cretePage(Pageable pageable, List<ImovelDTO> dtoList) {
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > dtoList.size() ? dtoList.size() : (start + pageable.getPageSize());

		Page<ImovelDTO> pages = new PageImpl<ImovelDTO>(dtoList.subList(start, end), pageable, dtoList.size());
		return pages;
	}

	public void verifyUser(Long id) {
		if (userRepository.userById(id) == null)
			throw new ResourceNotFoundException("Usuario nao encontrado para id " + id);
	}

}
