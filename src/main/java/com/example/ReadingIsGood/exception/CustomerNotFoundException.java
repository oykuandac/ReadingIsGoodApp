package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {

	private String message;

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String msg) {
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
