package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class InvalidPagingInputsException extends RuntimeException {

	private String message;

	public InvalidPagingInputsException() {
	}

	public InvalidPagingInputsException(String msg) {
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
