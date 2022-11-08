package com.example.ReadingIsGood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.validator.BookValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private BookService bookService;
	private BookValidator bookValidator;

	public BookController(BookService bookService, BookValidator bookValidator) {
		super();
		this.bookService = bookService;
		this.bookValidator = bookValidator;
	}

	@PostMapping("/createBook")
	public Book createBook(@RequestBody Book book) {
		try {
			bookValidator.validateBookInputs(book);
			return bookService.createBook(book);
		} catch (Exception ex) {
			logger.error("Unexpected exception occurred.", ex);
			throw ex;
		}
	}
}
