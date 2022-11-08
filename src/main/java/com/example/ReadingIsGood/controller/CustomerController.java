package com.example.ReadingIsGood.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.service.CustomerService;
import com.example.ReadingIsGood.validator.CustomerValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private CustomerService customerService;
	private CustomerValidator customerValidator;

	public CustomerController(CustomerService customerService, CustomerValidator customerValidator) {
		super();
		this.customerService = customerService;
		this.customerValidator = customerValidator;
	}

	@PostMapping("/createCustomer")
	public Object createCustomer(@RequestBody Customer customer) {
		logger.info("Request - create new customer: ", customer);
		try {
			customerValidator.validateCustomerEmailIsValid(customer);
			Customer customerByEmail = customerService.getCustomerByEmail(customer.getEmail());
			customerValidator.validateCustomerWithEmailExists(customerByEmail);
			return customerService.createCustomer(customer);
		} catch (Exception ex) {
			logger.error("Unexpected exception occurred.", ex);
			throw ex;
		}
	}

	@GetMapping("/listOrdersOfCustomer/{customerId}/{pageNo}/{pageSize}")
	public List<Order> listOrdersOfCustomer(@PathVariable Long customerId, @PathVariable int pageNo,
			@PathVariable int pageSize) {
		logger.info("Request - list orders of customer: ", customerId);
		try {
			customerValidator.validatePaging(pageNo, pageSize);
			return customerService.listAllOrdersOfCustomerId(customerId, pageNo, pageSize);
		} catch (Exception ex) {
			logger.error("Unexpected exception occurred.", ex);
			throw ex;
		}
	}

}
