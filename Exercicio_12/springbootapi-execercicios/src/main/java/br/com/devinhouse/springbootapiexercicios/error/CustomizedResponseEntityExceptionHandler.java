package br.com.devinhouse.springbootapiexercicios.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.devinhouse.springbootapiexercicios.exceptions.ProcessNotFoundException;
import br.com.devinhouse.springbootapiexercicios.exceptions.YearNotFoundException;

@RestControllerAdvice
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ProcessNotFoundException.class, Exception.class, HttpStatusCodeException.class,
			HttpClientErrorException.class })
	@Nullable
	public final ResponseEntity<Object> handleExceptionEntity(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse, BAD_REQUEST);

	}

	@ExceptionHandler(value = { YearNotFoundException.class })
	@Nullable
	public final ResponseEntity<Object> handleExceptionEntity(YearNotFoundException ynfe, WebRequest request)
			throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ynfe.getMessage(),
				request.getDescription(false), NOT_FOUND.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse, NOT_FOUND);

	}
}