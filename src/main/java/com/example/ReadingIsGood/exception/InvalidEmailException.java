package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class InvalidEmailException extends RuntimeException {

	private String message;

	public InvalidEmailException() {
	}

	public InvalidEmailException(String msg) {
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
