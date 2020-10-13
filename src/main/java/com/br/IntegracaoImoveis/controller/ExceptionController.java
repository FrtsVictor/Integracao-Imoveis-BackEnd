package com.br.IntegracaoImoveis.controller;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.IntegracaoImoveis.exceptions.EmailAlreadyExistsExeception;
import com.br.IntegracaoImoveis.exceptions.InvalidCredentialException;
import com.br.IntegracaoImoveis.exceptions.InvalidQueryException;
import com.br.IntegracaoImoveis.exceptions.UserNotExistsException;
import com.br.IntegracaoImoveis.model.User;


@RestControllerAdvice
public class ExceptionController  {


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	 
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));	     
	    return errors;
	}
		
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<User> invalidCredentials(InvalidCredentialException exception) {
		String msg = String.format("Invalid Credentials");
		return ResponseEntity.badRequest().header("X-Erro-msg", msg).header("X-Erro-code", exception.getMsg()).build();
	}
	
	
	@ExceptionHandler(InvalidQueryException.class)
	public ResponseEntity<String> invalidQuery(InvalidQueryException exception) {
		return ResponseEntity.badRequest().header("Error-msg", exception.getMsg()).header("Error-code", exception.getId().toString()).build();
	}
	
	
	@ExceptionHandler(UserNotExistsException.class)
	public ResponseEntity<String> userNotExists(UserNotExistsException exception) {
		return ResponseEntity.badRequest().header("Error-msg", exception.getMsg()).header("Error-code", exception.getId().toString()).build();
	}
	
	
	@ExceptionHandler(EmailAlreadyExistsExeception.class)
	public ResponseEntity<String> emailAlreadyExists(EmailAlreadyExistsExeception exception) {
		return ResponseEntity.badRequest().header("Error-msg", exception.getMsg()).header("Error-code", exception.getEmail().toString()).build();
	}
	
	
}
