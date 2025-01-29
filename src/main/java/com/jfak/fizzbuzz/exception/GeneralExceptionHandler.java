package com.jfak.fizzbuzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jfak.fizzbuzz.record.RestError;

@ControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<RestError> handleBadRequestException(BadRequestException e) {
		RestError errorResponse = new RestError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), "Invalid parameters", e.getMessage(), e.getHelpUrl(), e.getUri());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<RestError> handleNotFoundRequestException(NotFoundException e) {
		RestError errorResponse = new RestError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Not found", e.getMessage(), e.getHelpUrl(), e.getUri());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
}
