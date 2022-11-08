package com.example.ReadingIsGood.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.ReadingIsGood.controller.CustomerController;
import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.exception.CustomerNotFoundException;
import com.example.ReadingIsGood.exception.InvalidCustomerInputsException;
import com.example.ReadingIsGood.exception.InvalidEmailException;
import com.example.ReadingIsGood.exception.InvalidPagingInputsException;

@Component
public class CustomerValidator {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	public Customer validateCustomer(Customer customer) {
		if (customer == null) {
			String msg = "Customer not found!";
			logger.error(msg);
			throw new CustomerNotFoundException(msg);
		}
		return customer;
	}

	public void validateCustomerEmailIsValid(Customer customer) {
		if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
			String msg = "Invalid email: " + customer.getEmail();
			logger.error(msg);
			throw new InvalidEmailException(msg);
		}
	}

	public void validateCustomerWithEmailExists(Customer customer) {
		if (customer != null) {
			String msg = "Customer with this email is already in use: " + customer.getEmail();
			logger.error(msg, customer.getEmail());
			throw new InvalidCustomerInputsException(msg);
		}
	}

	public void validatePaging(int pageNo, int pageSize) {
		String msg = "Invalid paging inputs.";
		if (pageNo < 0 || pageSize < 1) {
			logger.error(msg);
			throw new InvalidPagingInputsException(msg);
		}
	}
}
