package br.com.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.error.exception.EmptyException;
import br.com.error.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> notFoundExceptionHandler(HttpServletRequest req, NotFoundException e) {
		ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(errorInfo);
	}
	
	@ExceptionHandler(EmptyException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> emptyExceptionHandler(HttpServletRequest req, EmptyException e) {
		ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorInfo);
	}

}
