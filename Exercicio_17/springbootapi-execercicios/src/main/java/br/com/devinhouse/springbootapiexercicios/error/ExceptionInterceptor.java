package br.com.devinhouse.springbootapiexercicios.error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomErrorApi.class)
	public final ResponseEntity<Object> handleAllExceptions(CustomErrorApi ex) {
		CustomErrorApiSchema exceptionResponse = new CustomErrorApiSchema(ex.getMessage(), ex.getDetails(),
				ex.getHint(), ex.getNextActions(), ex.getSupport());
		return new ResponseEntity<Object>(exceptionResponse, INTERNAL_SERVER_ERROR);
	}
}