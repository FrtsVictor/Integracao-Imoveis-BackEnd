package com.br.IntegracaoImoveis.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.mindrot.jbcrypt.BCrypt;
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

import com.br.IntegracaoImoveis.exceptions.EmailAlreadyExistsExeception;
import com.br.IntegracaoImoveis.exceptions.InvalidCredentialException;
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
		userService.userExists(id);
		User user = userRepository.userById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	
	@PostMapping
	public ResponseEntity<User> createuser(@Valid @RequestBody User user) throws EmailAlreadyExistsExeception {
		userService.emailAlreadyExists(user.getEmail());
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		user.setPassword(hashedPassword);
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@PostMapping( "/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody Map<String, Object> userMap) throws InvalidCredentialException {
		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");	
		User user = userService.validateEmail(email);
		
		User authUser = userService.validateLogin(user, password);	
		Map<String, String> map = new HashMap<>();
		map.put("message", "LoggedIn sucessfully");

		return new ResponseEntity<Map>(map, HttpStatus.OK);

	}
}
