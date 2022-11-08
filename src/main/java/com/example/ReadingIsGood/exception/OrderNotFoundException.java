package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {

	private String message;

	public OrderNotFoundException() {
	}

	public OrderNotFoundException(String msg) {
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
