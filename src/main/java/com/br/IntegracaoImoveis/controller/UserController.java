package com.br.IntegracaoImoveis.controller;



import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.IntegracaoImoveis.DTO.UserDTO;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.model.User;
import com.br.IntegracaoImoveis.repository.UserRepository;
import com.br.IntegracaoImoveis.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	UserService userService;
	
	public HttpHeaders Header() {
		HttpHeaders header = new HttpHeaders();
		header.add("IntegraçãoImoveis", "REST_API");
		return header;
	}

	@GetMapping("/users")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<Page<UserDTO>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
		Set<User> userSet = userRepository.findAll();
		List<UserDTO> usersDTO = userService.toCollectionDTO(userSet);
		return ResponseEntity.ok(userService.cretePage(pageable, usersDTO));	
	
	}

	
	@GetMapping("/users/{id}")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<UserDTO> getById(@Valid @PathVariable Long id) throws Exception {
		userService.userExists(id);
		UserDTO userDTO = userService.DTOtoModel(userRepository.userById(id));
		return ResponseEntity.ok(userDTO);
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
		User user = userRepository.userByUsername(username)
				.orElseThrow(()-> new ResourceNotFoundException("Username invalid"));
		return ResponseEntity.ok(userService.DTOtoModel(user));
	}
	
		
	@PostMapping("/sign-up")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user){
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		user.setPassword(hashedPassword);
		userRepository.save(user);
		return ResponseEntity.ok(userService.DTOtoModel(user));
	}

	
	@DeleteMapping("/admin/users/{id}")
	@Transactional(rollbackFor = Exception.class )
	public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long id) {
		userService.userExists(id);
		userRepository.deleteById(id);
		return  ResponseEntity.noContent().build();
	}
	
	
	
}
