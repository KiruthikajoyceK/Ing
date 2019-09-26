package com.hcl.bank.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(UserNotFoundException.class)

	public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(UserNotFoundException exception,

			WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage()

				);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(CommonException.class)

	public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(CommonException exception,

			WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage()

				);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
