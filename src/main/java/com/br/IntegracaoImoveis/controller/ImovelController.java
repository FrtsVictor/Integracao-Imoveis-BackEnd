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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.IntegracaoImoveis.DTO.ImovelDTO;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.CustomRepository;
import com.br.IntegracaoImoveis.repository.ImovelRepository;
import com.br.IntegracaoImoveis.repository.UserRepository;
import com.br.IntegracaoImoveis.service.ImovelService;


@RestController
@CrossOrigin(value  = "*")
@RequestMapping("/api/imoveis")
public class ImovelController {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	CustomRepository userCustomRepository;
	
	@Autowired ImovelService imovelService;
	
	@GetMapping()
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<Page<Imovel>> getAllUsers(@PageableDefault(size = 6)
													Pageable pageable) {
		return new ResponseEntity<>(imovelRepository.findAll(pageable),  HttpStatus.OK);
	}
	

	@GetMapping("/dto")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<Page<ImovelDTO>> listAllImoveis(@PageableDefault(size = 6) 
														  Pageable pageable){
		Set<Imovel> imvs = imovelRepository.findAll();			
		List<ImovelDTO> imvsDTO = imovelService.toDTOList(imvs);		
		return ResponseEntity.ok(imovelService.cretePage(pageable, imvsDTO));
	}
	
	
	@GetMapping("/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<ImovelDTO> getById(@Valid @PathVariable Long id) throws Exception {
		Imovel imv = imovelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Imovel não encontrado para id" + id));
		return ResponseEntity.ok(imovelService.imovelToDTO(imv));	
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
	
	
	@PostMapping("/user/{userID}/remove/{imovelID}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<@Valid Imovel> removeImovel(@Valid @PathVariable Long userID, 
													  @Valid @PathVariable Long imovelID){
		imovelService.verifyUser(userID);
		User user = userRepository.userById(userID);		
		Imovel imv = imovelRepository.findById(imovelID)
				.orElseThrow(()-> new ResourceNotFoundException("Imovel nao encontrado para id " + imovelID));
		user.removeImovel(imv);
		userRepository.save(user);
		return ResponseEntity.ok(imv);
	}

	
	@PostMapping("/user/{userID}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<@Valid Imovel> addImovel(@Valid @PathVariable Long userID, 
												   @Valid @RequestBody Imovel imovel){
		imovelService.verifyUser(userID);
		User user = userRepository.userById(userID);
		Imovel imvRequest = imovelRepository.findByIdImobile(imovel.getIdImobile());
		
		imovelService.verifyLikedImovel(user, imvRequest);
		
		if (imvRequest != null) {
			imvRequest.addUser(user);
			imovelRepository.save(imvRequest);
		}else {
			user.addImovel(imovel);
			userRepository.save(user);
		}
		return ResponseEntity.ok(imovel);
	}
	
		
	@GetMapping("/all/{userID}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<List<Imovel>> getFiltred(
				@PathVariable Long userID,
				@RequestParam(value = "cidade", required = false)String cidade,
				@RequestParam(value = "bairro", required = false) String bairro,
				@RequestParam(value = "tipo", required = false) String tipo
			){
		imovelService.verifyUser(userID);
		 List<Imovel> imvs = userCustomRepository.find( userID, cidade, bairro, tipo);
		 return ResponseEntity.ok(imvs); 	
	}
	
	
	@GetMapping("/bairros/{userID}")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<?> findBairros( @PathVariable Long userID){
		imovelService.verifyUser(userID);
		 Set<?> imvs = imovelRepository.findAllCidadeBairro(userID);
		return ResponseEntity.ok(imvs);
	}
	
	
}
