package com.br.IntegracaoImoveis.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.DTO.ImovelDTO;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.ImovelRepository;
import com.br.IntegracaoImoveis.repository.UserRepository;
import com.br.IntegracaoImoveis.service.ImovelService;


@RestController
@RequestMapping("/api/imoveis")
public class ImovelController {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired ImovelService imovelService;
	

	@GetMapping("/users")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<?> getAllUsers(@PageableDefault(size = 6) Pageable pageable) {
		return new ResponseEntity<>(imovelRepository.findAll(pageable),  HttpStatus.OK);
	}
	

	@GetMapping("/dto")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<Page<ImovelDTO>> listAllImoveis(@PageableDefault(size = 6) Pageable pageable){
		Set<Imovel> imvs = imovelRepository.findAll();			
		List<ImovelDTO> imvsDTO = imovelService.toDTOList(imvs);		
		return ResponseEntity.ok(imovelService.cretePage(pageable, imvsDTO));
	}
	
	
	@GetMapping("/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<ImovelDTO> getById(@Valid @PathVariable Long id) throws Exception {
		Imovel imv = imovelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Imovel não encontrado para id" + id));
		return ResponseEntity.ok(imovelService.toDTOClass(imv));	
	}
	

	@GetMapping("/user/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<Page<ImovelDTO>> getAllImoveisByUserId(
							@Valid @PathVariable Long id,
							@PageableDefault(size = 6) Pageable pageable) {
		imovelService.verifyUser(id);
		User user = userRepository.userById(id);
		List<ImovelDTO> imvsDTO = imovelService.toDTOList(user.getImoveis());
		return ResponseEntity.ok(imovelService.cretePage(pageable, imvsDTO));
	
	}
	
	@PutMapping("/user/remove/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<@Valid Imovel> removeImovel(@Valid @PathVariable Long idUser, 
													@Valid @PathVariable Long idImovel){
		imovelService.verifyUser(idUser);
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
		imovelService.verifyUser(id);
		User user = userRepository.userById(id);
		
		Imovel imvRequest = imovelRepository.findByIdImobile(imovel.getIdImobile());
		
		if (imvRequest != null) {
			imvRequest.addUser(user);
			imovelRepository.save(imvRequest);
		}else {
			user.addImovel(imovel);
			userRepository.save(user);
		}
		return ResponseEntity.ok(imovel);
	}
	
}
