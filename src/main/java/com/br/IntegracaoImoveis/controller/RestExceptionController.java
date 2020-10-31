package com.br.IntegracaoImoveis.controller;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.br.IntegracaoImoveis.exceptions.ErrorDetailsPattern;
import com.br.IntegracaoImoveis.exceptions.ImovelAlreadyLikedException;
import com.br.IntegracaoImoveis.exceptions.Problem;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundDetails;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler  {

	
	@Autowired
	private MessageSource messageSource;
	

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){
	ResourceNotFoundDetails rnfdetails = ResourceNotFoundDetails.Builder
			.newBuilder()
			.timestamp(OffsetDateTime.now())
			.status(HttpStatus.NOT_FOUND.value())
			.title("Busca Nao Encontrada")
			.detail(rnfException.getMsg())
			.developerMessage(rnfException.getClass().getName())
			.build();
	return new ResponseEntity<>(rnfdetails, HttpStatus.NOT_FOUND);
}


@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
	ArrayList<com.br.IntegracaoImoveis.exceptions.Problem.Campo> campos = new ArrayList<Problem.Campo>();
	
	for (ObjectError error : ex.getBindingResult().getAllErrors()) {
	String nome = ((FieldError) error).getField();
		String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
		campos.add(new Problem.Campo(nome,mensagem));
	}
	
	Problem problem = Problem.builder()
			.status(status.value())
			.titulo("Um ou mais campos invalidos")
			.dataHora(OffsetDateTime.now())
			.campos(campos)
			.build();
	return super.handleExceptionInternal(ex, problem, headers, status, request);
}   


   @Override
   protected ResponseEntity<Object> handleExceptionInternal(Exception ex, 
		   	@Nullable Object body,
		   	HttpHeaders headers,
		   	HttpStatus status,
		   	WebRequest request) {
	   ErrorDetailsPattern errorDetails = ResourceNotFoundDetails.Builder
		    .newBuilder()
			.timestamp(OffsetDateTime.now())
			.status(status.value())
			.title("Internal Exception")
			.detail(ex.getMessage())
			.developerMessage(ex.getClass().getName())
			.build();
		return new ResponseEntity<>(errorDetails, headers, status);
	}

      
   @ExceptionHandler(Exception.class)
   public final ResponseEntity<ErrorDetailsPattern> handleAllExceptions(Exception ex,  WebRequest request)  {
	   ErrorDetailsPattern errorDetails = ErrorDetailsPattern.Builder
		   .newBuilder()
		   .timestamp(OffsetDateTime.now())
		   .detail(ex.getMessage())
		   .developerMessage(ex.getClass().getName())
		   .status(500)
		   .title("Internal Server Error")
		   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   
   @ExceptionHandler(RuntimeException.class)
   public final ResponseEntity<ErrorDetailsPattern> handleRuntimeException(Exception ex,  WebRequest request)  {
	   ErrorDetailsPattern errorDetails = ErrorDetailsPattern.Builder
		   .newBuilder()
		   .timestamp(OffsetDateTime.now())
		   .detail(ex.getMessage())
		   .developerMessage(ex.getClass().getName())
		   .status(500)
		   .title("Internal Server Error")
		   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
      
   @ExceptionHandler(IOException.class)
   public final ResponseEntity<ErrorDetailsPattern> handleIOException(IOException ex,  WebRequest request)  {
	   ErrorDetailsPattern errorDetails = ErrorDetailsPattern.Builder
		   .newBuilder()
		   .timestamp(OffsetDateTime.now())
		   .detail(ex.getCause().getMessage())
		   .developerMessage(ex.getClass().getName())
		   .status(500)
		   .title("Internal Server Error")
		   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   
   
   @ExceptionHandler(DataIntegrityViolationException.class)
   public final ResponseEntity<ErrorDetailsPattern> handleConstraintViolation(
		   DataIntegrityViolationException ex,
		   WebRequest request)  {	   
	   ErrorDetailsPattern errorDetails = ErrorDetailsPattern.Builder
		   .newBuilder()
		   .timestamp(OffsetDateTime.now())
		   .detail(ex.getRootCause().getMessage())
		   .developerMessage(ex.getClass().getName())
		   .status(401)
		   .title(ex.getCause().getMessage())
		   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
   }
        

   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler(ImovelAlreadyLikedException.class)
   public final ResponseEntity<ErrorDetailsPattern> handleImovelAlreadyLiked (ImovelAlreadyLikedException imv){
	   String msg = String.format(imv.getMsg() + imv.getId()); 
	  
	   ErrorDetailsPattern errorDetails = ErrorDetailsPattern.Builder
		   .newBuilder()
		   .timestamp(OffsetDateTime.now())
		   .detail(msg)
		   .status(409)
		   .title("CONFLICT")
		   .build(); 
	   return new ResponseEntity<ErrorDetailsPattern>(errorDetails, HttpStatus.CONFLICT);
   }
   
    
   
   
}
