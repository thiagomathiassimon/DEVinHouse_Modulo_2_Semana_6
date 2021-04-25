package br.com.devinhouse.springbootapiexercicios.error;

import br.com.devinhouse.springbootapiexercicios.exceptions.IllegalYearFormatException;
import br.com.devinhouse.springbootapiexercicios.exceptions.ProcessNotFoundException;
import br.com.devinhouse.springbootapiexercicios.exceptions.YearNotFoundException;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ProcessNotFoundException.class})
	public final ResponseEntity<Object> handleExceptionEntity(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse, BAD_REQUEST);

	}

	@ExceptionHandler(value = { YearNotFoundException.class })
	public final ResponseEntity<Object> handleExceptionEntity(YearNotFoundException ynfe, WebRequest request)
			throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ynfe.getMessage(),
				request.getDescription(false), NOT_FOUND.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse, NOT_FOUND);

	}

	@ExceptionHandler(value = { IllegalYearFormatException.class})
	public final ResponseEntity<Object> handleExceptionEntity(IllegalYearFormatException iyfe, WebRequest request)
			throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), iyfe.getMessage(),
				request.getDescription(false), INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse, INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = { NumberFormatException.class})
	public final ResponseEntity<Object> handleExceptionEntity(NumberFormatException nfe, WebRequest request)
			throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), nfe.getMessage(),
				request.getDescription(false), INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse, INTERNAL_SERVER_ERROR);

	}

}