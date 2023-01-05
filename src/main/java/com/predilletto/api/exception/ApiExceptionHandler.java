package com.predilletto.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class) 
	public ResponseEntity<Object> test (Exception e) {
		Map<String, String> m =new HashMap<>();
		m.put("Error",e.getMessage());
		return new ResponseEntity<Object>(m, HttpStatus.BAD_REQUEST); 
	}
	
}
