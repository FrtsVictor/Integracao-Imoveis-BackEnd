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

import com.br.IntegracaoImoveis.DTO.UserDTO;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.UserRepository;

@Component
@Service
public class UserService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;
	

	public UserDTO DTOtoModel(User usr) {
		return modelMapper.map(usr, UserDTO.class);
	}

	public List<UserDTO> toCollectionDTO(Set<User> userList) {
		Set<UserDTO> dtoSet = new HashSet<>();
		for (User usr : userList) {
			UserDTO usrDTO = DTOtoModel(usr);
			dtoSet.add(usrDTO);
		}
		List<UserDTO> dtoList = new ArrayList<>(dtoSet);
		return dtoList;
	}

	public Page<UserDTO> cretePage(Pageable pageable, List<UserDTO> dtoList) {
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > dtoList.size() ? dtoList.size() : (start + pageable.getPageSize());

		Page<UserDTO> pages = new PageImpl<UserDTO>(dtoList.subList(start, end), pageable, dtoList.size());
		return pages;
	}

	public void userExists(Long id) {
		if (userRepository.userById(id) == null)
			throw new ResourceNotFoundException("Usuario nao encontrado para id " + id);
	}

}
