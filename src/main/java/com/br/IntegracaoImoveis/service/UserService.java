package com.br.IntegracaoImoveis.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.IntegracaoImoveis.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepository;

	
//	public User validateEmail(String email) throws InvalidCredentialException {
//		User user = userRepository.userByEmail(email);
//		if(user == null) {
//			throw new InvalidCredentialException("Invalid user");
//		}
//		return user;
//	}
//	
//	
//	public User validateLogin(User user, String password) throws InvalidCredentialException {
//		System.out.println(BCrypt.checkpw(password, user.getPassword()));
//		if(!BCrypt.checkpw(password, user.getPassword())) {
//			throw new InvalidCredentialException("Login or Password Wrong");
//		}
//		return user;
//	}
//	
//	
//	public void userExists(Long id) throws InvalidQueryException, UserNotExistsException {
//		if(id < 0)throw new InvalidQueryException("ID cant be less than 0", id); 
//		User user = userRepository.userById(id);
//		if(user == null) throw new UserNotExistsException("Not found any user with this this ID", id);
//	}
//	
//	
//	public void emailAlreadyExists(String email) throws EmailAlreadyExistsExeception {		
//		User user = userRepository.userByEmail(email);
//		if(user !=null) throw new EmailAlreadyExistsExeception("Email already in use", email);
//	} 
//	
	
}
