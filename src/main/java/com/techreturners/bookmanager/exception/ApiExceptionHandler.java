package com.techreturners.bookmanager.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {
	HttpStatus badRequest = HttpStatus.BAD_REQUEST;

	@ExceptionHandler(value = { ApiRequestException.class })
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
		ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, badRequest);
	}
	
	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<Object> NoSuchElementException(NoSuchElementException e) {
		ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, badRequest);
	}
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public ResponseEntity<Object> EmptyResultDataAccessException(EmptyResultDataAccessException e) {
		ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(apiException, badRequest);
	}

}
