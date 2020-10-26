package com.br.IntegracaoImoveis.controller;


import java.awt.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.repository.ImovelRepository;



@RestController
@RequestMapping("/api/imoveis")
public class ImovelController {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	
	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("IntegraçãoImoveis", "REST_API");
		return header;
	}
	
	@GetMapping
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<?> getAllUsers(@PageableDefault(size = 6) Pageable pageable) {
		return new ResponseEntity<>(imovelRepository.findAll(pageable),  HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Imovel> getById(@Valid @PathVariable Long id) throws Exception {
		Imovel Imovel = imovelRepository.findById(id)
				.orElseThrow(() -> new Exception("Imovel " + id + " not found"));
		return new ResponseEntity<Imovel>(Imovel, Header(), HttpStatus.OK);		
	}
	

	@GetMapping("/user/{id}")
	@Transactional(rollbackFor = Exception.class )	
	public ResponseEntity<?> getAllByUserId(@PageableDefault(size = 10) Long id, Pageable pageable) {
		List list = imovelRepository.imovelByUsername(id);
		System.out.println(list);
		return new ResponseEntity<>(list,  HttpStatus.OK);
	}
	

	
}
