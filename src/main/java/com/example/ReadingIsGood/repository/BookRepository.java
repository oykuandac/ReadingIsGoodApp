package com.example.ReadingIsGood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReadingIsGood.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByBookId(Long bookId);


}
