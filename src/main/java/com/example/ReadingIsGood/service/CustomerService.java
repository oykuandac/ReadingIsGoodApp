package com.example.ReadingIsGood.service;

import java.util.List;

import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;

public interface CustomerService {

	public List<Order> listAllOrdersOfCustomerId(Long customerId, int pageNo, int pageSize);
	
	public Customer createCustomer(Customer customer);
	
	public Customer getCustomerByEmail(String email);
	
	public Customer getCustomerById(Long id);
}
