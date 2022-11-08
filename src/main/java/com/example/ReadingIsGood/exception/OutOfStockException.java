package com.example.ReadingIsGood.exception;

@SuppressWarnings("serial")
public class OutOfStockException extends RuntimeException {

	private String message;

	public OutOfStockException() {
	}

	public OutOfStockException(String msg) {
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
