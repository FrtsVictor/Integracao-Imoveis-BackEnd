package com.br.IntegracaoImoveis.controller;

import javax.validation.Valid;

import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.repository.ImovelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/imovel")
public class ImovelController {

	
	@Autowired
	ImovelRepository ImovelRepository;
	
	
	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("IntegraçãoImoveis", "REST_API");
		return header;
	}
	
	
	@GetMapping
	public ResponseEntity<?>getAllImovels(@PageableDefault(size = 10) Pageable pageable){
		return new ResponseEntity<>(ImovelRepository.findAll(pageable), Header(), HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Imovel> getById(@Valid @PathVariable Long id) throws Exception {
		Imovel Imovel = ImovelRepository.findById(id)
				 .orElseThrow(() -> new Exception("Imovel " + id + " not found"));
	    return new ResponseEntity<Imovel>(Imovel, Header(), HttpStatus.OK);
		
	}
	
	@PostMapping
	  public Imovel createImovel(@Valid @RequestBody Imovel Imovel) {
	    return ImovelRepository.save(Imovel);
	  }

	
}
