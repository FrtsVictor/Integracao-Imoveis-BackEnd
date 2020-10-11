package com.br.IntegracaoImoveis.controller;

import java.util.List;
import javax.validation.Valid;

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

import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.UserRepository;



@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	UserRepository userRepository;
	
	
	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("IntegraçãoImoveis", "REST_API");
		return header;
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>>getAllUsers(){
		return new ResponseEntity<List<User>>(userRepository.findAll(), Header(), HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) throws Exception {
		User user = userRepository.findById(id)
				 .orElseThrow(() -> new Exception("User " + id + " not found"));
	    return new ResponseEntity<User>(user, Header(), HttpStatus.OK);
		
	}
	
	@PostMapping
	  public User createuser(@Valid @RequestBody User user) {
	    return userRepository.save(user);
	  }

	
}
