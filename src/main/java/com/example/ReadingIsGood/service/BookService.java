package com.example.ReadingIsGood.service;

import com.example.ReadingIsGood.entity.Book;

public interface BookService {

	Book createBook(Book book);

	Book getBookById(Long bookId);
	
	Book saveBook(Book book);
}
