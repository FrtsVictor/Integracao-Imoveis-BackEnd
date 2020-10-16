package com.br.IntegracaoImoveis.controller;



import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.exceptions.EmailAlreadyExistsExeception;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("IntegraçãoImoveis", "REST_API");
		return header;
	}

	@GetMapping("/users")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<?> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
		return new ResponseEntity<>(userRepository.findAll(pageable),  HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<User> getById(@Valid @PathVariable Long id) throws Exception {
		verifyIdUserExists(id);
		User user = userRepository.userById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws EmailAlreadyExistsExeception{
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		user.setPassword(hashedPassword);
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	
	@DeleteMapping("/admin/users/{id}")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<User> deleteUser(@Valid @PathVariable Long id) {
		verifyIdUserExists(id);
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	private void verifyIdUserExists(Long id) {
		if (userRepository.userById(id) == null)
			throw new ResourceNotFoundException("User not Found for id" + id);
	}
	
	
	
}
