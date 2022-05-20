package com.softtek.clinica.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3654688366600382L;

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
}
