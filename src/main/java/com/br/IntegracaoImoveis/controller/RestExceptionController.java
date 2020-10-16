package com.br.IntegracaoImoveis.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

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

import com.br.IntegracaoImoveis.exceptions.EmailAlreadyExistsExeception;
import com.br.IntegracaoImoveis.exceptions.ErrorDetails;
import com.br.IntegracaoImoveis.exceptions.InvalidQueryException;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundDetails;
import com.br.IntegracaoImoveis.exceptions.ResourceNotFoundException;
import com.br.IntegracaoImoveis.exceptions.UserNotExistsException;
import com.br.IntegracaoImoveis.exceptions.ValidationErrorDetails;


@ControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler  {


@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){
	ResourceNotFoundDetails rnfdetails = ResourceNotFoundDetails.Builder
	.newBuilder()
	.timestamp(new Date().getTime())
	.status(HttpStatus.NOT_FOUND.value())
	.title("Resource Not Found")
	.detail(rnfException.getMessage())
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
           errors.put(error.getField(), error.getDefaultMessage()));	     
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
	   ErrorDetails errorDetails = ResourceNotFoundDetails.Builder
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
   public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex,  WebRequest request)  {
	   ErrorDetails errorDetails = ErrorDetails.Builder
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
   public final ResponseEntity<ErrorDetails> handleRuntimeException(Exception ex,  WebRequest request)  {
	   ErrorDetails errorDetails = ErrorDetails.Builder
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
   public final ResponseEntity<ErrorDetails> handleIOException(IOException ex,  WebRequest request)  {
	   ErrorDetails errorDetails = ErrorDetails.Builder
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
   public final ResponseEntity<ErrorDetails> handleConstraintViolation(DataIntegrityViolationException ex,
		   																WebRequest request)  {
	   System.out.println("message_ " + ex.getLocalizedMessage());
	   System.out.println("message_ " + ex.getMessage());
	   System.out.println("cause_ " + ex.getCause());
	   System.out.println("class_ " + ex.getClass());
	   System.out.println("trace_ " + ex.getStackTrace());
	   System.out.println("root_ " + ex.getRootCause());
	   
	   ErrorDetails errorDetails = ErrorDetails.Builder
			   .newBuilder()
			   .timestamp(new Date().getTime())
			   .detail(ex.getRootCause().getMessage())
			   .developerMessage(ex.getClass().getName())
			   .status(401)
			   .title(ex.getCause().getMessage())
			   .build(); 
	   return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
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


//   @ResponseStatus(HttpStatus.FORBIDDEN)
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleMethodArgumentNotValid(Exception ex) {
//	   ErrorDetails errorDetails = ErrorDetails.Builder
//			   .newBuilder()
//				.timestamp(new Date().getTime())
//				.detail(ex.getMessage())
//				.developerMessage(ex.getClass().getName())
//				.status(500)
//				.title("Internal Server Error")
//				.build(); 
//	   return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
//	}
//   
      
   
   
   
}
