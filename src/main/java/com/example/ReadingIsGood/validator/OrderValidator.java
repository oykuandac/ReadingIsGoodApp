package com.example.ReadingIsGood.validator;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.ReadingIsGood.controller.OrderController;
import com.example.ReadingIsGood.exception.InvalidDateException;
import com.example.ReadingIsGood.exception.InvalidEmailException;

@Component
public class OrderValidator {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	public Object validateOrderById(Object order) {
		if (order == null) {
			String msg = "Order not found: ";
			logger.error(msg);
			throw new InvalidEmailException(msg);
		}
		return order;
	}

	public void validateDates(Date startDate, Date endDate) {
		if (startDate.after(endDate)) {
			String msg = "Start date cannot be later than end date.";
			logger.error(msg);
			throw new InvalidDateException(msg);
		}
	}
}
