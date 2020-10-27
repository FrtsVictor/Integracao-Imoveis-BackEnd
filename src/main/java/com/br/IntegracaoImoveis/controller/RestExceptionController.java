package com.br.IntegracaoImoveis.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.br.IntegracaoImoveis.exceptions.ErrorDetailsPattern;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundDetails;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.exceptions.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler  {

	
	@Autowired
	private MessageSource messageSource;
	

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){
	ResourceNotFoundDetails rnfdetails = ResourceNotFoundDetails.Builder
	.newBuilder()
	.timestamp(new Date().getTime())
	.status(HttpStatus.NOT_FOUND.value())
	.title("Busca Nao Encontrada")
	.detail(rnfException.getMsg())
	.developerMessage(rnfException.getClass().getName())
	.build();
	
	return new ResponseEntity<>(rnfdetails, HttpStatus.NOT_FOUND);
}


   @Override
   public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
       
       Map<String, String> errors = new HashMap<>();
       
       
       manvException.getBindingResult().getFieldErrors().forEach(error -> 
           errors.put(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())));	
       
//       manvException.getBindingResult().getFieldErrors().forEach(error -> 
//       errors.put(error.getField(), error.getDefaultMessage()));	
//       
       
       ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder
               .newBuilder()
               .timestamp(new Date().getTime())
               .status(HttpStatus.BAD_REQUEST.value())
               .title("Error: Validacao dos campos")
               .detail("BAD REQUEST")
               .developerMessage(manvException.getClass().getName())
               .resume(errors)
               .build();
       return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
   }
      
   
   @Override
   protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
		   														HttpHeaders headers,
		   														HttpStatus status,
		   														WebRequest request) {
	   ErrorDetailsPattern errorDetails = ResourceNotFoundDetails.Builder
			   .newBuilder()
				.timestamp(new Date().getTime())
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
			   .timestamp(new Date().getTime())
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
			   .timestamp(new Date().getTime())
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
			   .timestamp(new Date().getTime())
			   .detail(ex.getCause().getMessage())
			   .developerMessage(ex.getClass().getName())
			   .status(500)
			   .title("Internal Server Error")
			   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler(DataIntegrityViolationException.class)
   public final ResponseEntity<ErrorDetailsPattern> handleConstraintViolation(DataIntegrityViolationException ex,
		   																WebRequest request)  {
	   System.out.println("message_ " + ex.getLocalizedMessage());
	   System.out.println("message_ " + ex.getMessage());
	   System.out.println("cause_ " + ex.getCause());
	   System.out.println("class_ " + ex.getClass());
	   System.out.println("trace_ " + ex.getStackTrace());
	   System.out.println("root_ " + ex.getRootCause());
	   
	   ErrorDetailsPattern errorDetails = ErrorDetailsPattern.Builder
			   .newBuilder()
			   .timestamp(new Date().getTime())
			   .detail(ex.getRootCause().getMessage())
			   .developerMessage(ex.getClass().getName())
			   .status(401)
			   .title(ex.getCause().getMessage())
			   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
   }
        
}