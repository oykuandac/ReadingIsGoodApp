package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class InvalidCustomerInputsException extends RuntimeException {

	private String message;

	public InvalidCustomerInputsException() {
	}

	public InvalidCustomerInputsException(String msg) {
		super(msg);
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
