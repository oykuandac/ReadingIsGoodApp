package com.example.ReadingIsGood.model;

import java.util.List;

import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.entity.Customer;

public class OrderResponse {

	private Long orderId;
	private Customer customer;
	private List<Book> orderedBooks;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Book> getOrderedBooks() {
		return orderedBooks;
	}

	public void setOrderedBooks(List<Book> orderedBooks) {
		this.orderedBooks = orderedBooks;
	}

}
