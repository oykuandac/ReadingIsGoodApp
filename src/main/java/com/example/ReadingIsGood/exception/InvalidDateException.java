package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class InvalidDateException extends RuntimeException {

	private String message;

	public InvalidDateException() {
	}

	public InvalidDateException(String msg) {
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
