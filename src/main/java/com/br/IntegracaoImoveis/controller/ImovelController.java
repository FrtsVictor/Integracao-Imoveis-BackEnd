package com.br.IntegracaoImoveis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.model.Imovel;
import com.br.IntegracaoImoveis.repository.ImovelRepository;



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
	public ResponseEntity<List<Imovel>>getAllImovels(){
		return new ResponseEntity<List<Imovel>>(ImovelRepository.findAll(), Header(), HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Imovel> getById(@PathVariable Long id) throws Exception {
		Imovel Imovel = ImovelRepository.findById(id)
				 .orElseThrow(() -> new Exception("Imovel " + id + " not found"));
	    return new ResponseEntity<Imovel>(Imovel, Header(), HttpStatus.OK);
		
	}
	
	@PostMapping
	  public Imovel createImovel(@RequestBody Imovel Imovel) {
	    return ImovelRepository.save(Imovel);
	  }

	
}
