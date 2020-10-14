package com.br.IntegracaoImoveis.controller;



import java.util.List;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.UserRepository;
import com.br.IntegracaoImoveis.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {


	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("IntegraçãoImoveis", "REST_API");
		return header;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userRepository.findAll(),  HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) throws Exception {
		verifyIdUserExists(id);
		User user = userRepository.userById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//used for rollback on unchecked exceptions
	@PostMapping
	@Transactional(rollbackFor = Exception.class )
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		user.setPassword(hashedPassword);
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		verifyIdUserExists(id);
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	
	private void verifyIdUserExists(Long id) {
		if (userRepository.userById(id) == null)
			throw new ResourceNotFoundException("User not Found for id" + id);
	}
	
	
	
}
