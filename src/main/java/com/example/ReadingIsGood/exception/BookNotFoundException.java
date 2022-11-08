package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {

	private String message;

	public BookNotFoundException() {
	}

	public BookNotFoundException(String msg) {
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
