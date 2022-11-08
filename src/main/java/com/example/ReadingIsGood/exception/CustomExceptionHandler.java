package com.example.ReadingIsGood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidCustomerInputsException.class)
	protected ResponseEntity<Object> handleCustomerWithEmailAlreadyExists(InvalidCustomerInputsException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(InvalidEmailException.class)
	protected ResponseEntity<Object> handleInvalidEmail(InvalidEmailException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(InvalidPagingInputsException.class)
	protected ResponseEntity<Object> handleInvalidPagingInputs(InvalidPagingInputsException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(OrderNotFoundException.class)
	protected ResponseEntity<Object> handleOrderNotFound(OrderNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(InvalidDateException.class)
	protected ResponseEntity<Object> handleInvalidDate(InvalidDateException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(OutOfStockException.class)
	protected ResponseEntity<Object> handleOutOfStock(OutOfStockException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(CustomerNotFoundException.class)
	protected ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}

	@ExceptionHandler(BookNotFoundException.class)
	protected ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		return new ResponseEntity<>(error, error.getHttpStatus());

	}
}
