package com.br.IntegracaoImoveis.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.DTO.ImovelDTO;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.ImovelRepository;
import com.br.IntegracaoImoveis.repository.UserRepository;


@RestController
@RequestMapping("/api/imoveis")
public class ImovelController {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@GetMapping
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<?> getAllUsers(@PageableDefault(size = 6) Pageable pageable) {
		return new ResponseEntity<>(imovelRepository.findAll(pageable),  HttpStatus.OK);
	}
	
	@GetMapping("/dto")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<Page<ImovelDTO>> listAllImoveis(@PageableDefault(size = 6) Pageable pageable){
		Set<Imovel> imvs = imovelRepository.findAll();	
		Set<ImovelDTO> dtoList= toCollectionDTO(imvs);		
		List<ImovelDTO> targetList = new ArrayList<>(dtoList);		
		
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > targetList.size() ? targetList.size() : (start + pageable.getPageSize());
		
		Page<ImovelDTO> pages = new PageImpl<ImovelDTO>(targetList.subList(start, end), pageable, targetList.size());	
		
		return ResponseEntity.ok(pages);	
	}
	
	@GetMapping("/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<ImovelDTO> getById(@Valid @PathVariable Long id) throws Exception {
		Imovel imovel = imovelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado para id " + id));
		return ResponseEntity.ok(DTOtoModel(imovel));	
	}
	

	@GetMapping("/users/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<Page<ImovelDTO>> getAllByUserId(
							@Valid @PathVariable Long id,
							@PageableDefault(size = 6) Pageable pageable
			) {
		verifyIdUserExists(id);
		User user = userRepository.userById(id);
		Set<ImovelDTO> dts = toCollectionDTO(user.getImoveis());
		List<ImovelDTO> targetList = new ArrayList<>(dts);		
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > targetList.size() ? targetList.size() : (start + pageable.getPageSize());
		

		Page<ImovelDTO> pages = new PageImpl<ImovelDTO>(targetList.subList(start, end), pageable, targetList.size());				
		return ResponseEntity.ok(pages);
		//		Page<ImovelDTO> page = new PageImpl<>(targetList);		
		//		List<?> dtoList = dtoPage.getContent().stream().map(asd -> DTOtoModel(asd)).collect(Collectors.toList());	
	}

	
	
	@PutMapping("/user/remove/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<@Valid Imovel> removeImovel(@Valid @PathVariable Long idUser, 
													@Valid @PathVariable Long idImovel){
		verifyIdUserExists(idUser);
		User user = userRepository.userById(idUser);		
		Imovel imv = imovelRepository.findById(idImovel)
				.orElseThrow(()-> new ResourceNotFoundException("Imovel nao encontrado para id " + idImovel));
		user.removeImovel(imv);
		userRepository.save(user);
		return ResponseEntity.ok(imv);
	}
	
	

	@PostMapping("/user/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<@Valid Imovel> addImovel(@Valid @PathVariable Long id, 
										@Valid @RequestBody Imovel imovel){
		verifyIdUserExists(id);
		User user = userRepository.userById(id);
		
		Imovel imovelRegistred = imovelRepository.findByIdImobile(imovel.getIdImobile());
		
		if (imovelRegistred != null) {
			imovelRegistred.addUser(user);
			imovelRepository.save(imovelRegistred);
		}else {
			user.addImovel(imovel);
			userRepository.save(user);
		}
		return ResponseEntity.ok(imovel);
	}
	
	

	private ImovelDTO DTOtoModel(Imovel imovel) {
		return modelMapper.map(imovel, ImovelDTO.class); 
	}

	private Set<ImovelDTO> toCollectionDTO (Set<Imovel> lista){
		Set<ImovelDTO> lista2 = new HashSet<>();		 
		 for(Imovel imv : lista) {
			 ImovelDTO imvs = DTOtoModel(imv);
			 lista2.add(imvs);
		 }
		 return lista2;
	}	

	private void verifyIdUserExists(Long id) {
		if (userRepository.userById(id) == null)
			throw new ResourceNotFoundException("Usuario nao encontrado para id " + id);
	}

	
}
