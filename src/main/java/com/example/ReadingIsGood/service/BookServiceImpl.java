package com.example.ReadingIsGood.service;

import org.springframework.stereotype.Service;

import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book getBookById(Long bookId) {
		return bookRepository.findByBookId(bookId);
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
}
