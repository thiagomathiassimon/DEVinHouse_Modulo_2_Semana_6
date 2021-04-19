package br.com.devinhouse.springbootapiexercicios.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ProcessNotFoundException extends RuntimeException {

	public ProcessNotFoundException(String message) {
		super(message);
	}

}
