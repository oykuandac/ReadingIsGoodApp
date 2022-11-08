package com.example.ReadingIsGood.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Order> listAllOrdersOfCustomerId(Long customerId, int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		return customerRepository.findAllByCustomerId(paging, customerId);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findByCustomerId(id);
	}
}
