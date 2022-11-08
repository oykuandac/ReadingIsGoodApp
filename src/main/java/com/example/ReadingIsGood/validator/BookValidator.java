package com.example.ReadingIsGood.validator;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.ReadingIsGood.controller.BookController;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.exception.BookNotFoundException;
import com.example.ReadingIsGood.exception.InvalidBookInputsException;
import com.example.ReadingIsGood.exception.OutOfStockException;

@Component
public class BookValidator {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	public void validateBookInputs(Book book) {
		if (book.getPrice() < 0) {
			String msg = "Book price cannot be less than 0.";
			logger.error(msg);
			throw new InvalidBookInputsException(msg);
		}
		if (book.getStock() < 0) {
			String msg = "Book stock cannot be less than 0.";
			logger.error(msg);
			throw new InvalidBookInputsException(msg);
		}
	}

	public void validateBook(Entry<Long, Integer> bookMap, Book book) {
		if (book == null) {
			String msg = "Book not found!";
			logger.error(msg);
			throw new BookNotFoundException(msg);
		}
		if (!isBookStockEnough(book, bookMap.getValue())) {
			String msg = "Not enough stock for this book.";
			logger.error(msg);
			throw new OutOfStockException(msg);
		}
		if (bookMap.getValue() == -1) {
			String msg = "Not a valid amount. Please try again.";
			logger.error(msg);
			throw new InvalidBookInputsException(msg);
		}
	}

	private boolean isBookStockEnough(Book book, Integer amount) {
		return book.getStock() > amount;
	}
}
