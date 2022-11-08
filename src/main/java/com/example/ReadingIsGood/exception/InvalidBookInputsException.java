package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class InvalidBookInputsException extends RuntimeException {

	private String message;

	public InvalidBookInputsException() {
	}

	public InvalidBookInputsException(String msg) {
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
