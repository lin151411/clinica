package com.softtek.clinica.backend.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.metadata.descriptor.ExecutableDescriptorImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> manejarTodasExceptions(Exception ex, WebRequest request){
		
		ExceptionResponse err= new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ModeloNotFoundException.class)
	public ResponseEntity<ExceptionResponse> manejarModelNotFoundException(ModeloNotFoundException ex, WebRequest request){
		
		ExceptionResponse err= new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensaje= ex.getBindingResult() //devuelve un array de errores
				.getAllErrors()//obtiene todos los errores
				.stream()//mapeamos el stream de todos los errores
				.map(errorRecibido->{//cada error se separa por comas
					return errorRecibido.getDefaultMessage().concat(",");
				}).collect(Collectors.joining());//convierte lo recibido a String
		
	ExceptionResponse err= new ExceptionResponse(LocalDateTime.now(), mensaje, request.getDescription(false));
		
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	

}
