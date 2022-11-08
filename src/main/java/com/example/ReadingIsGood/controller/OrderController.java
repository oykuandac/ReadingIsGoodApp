package com.example.ReadingIsGood.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.model.OrderRequest;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.validator.OrderValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private OrderService orderService;
	private OrderValidator orderValidator;

	public OrderController(OrderService orderService, OrderValidator orderValidator) {
		super();
		this.orderService = orderService;
		this.orderValidator = orderValidator;
	}

	@PostMapping("/createOrder")
	public Object createOrder(@RequestBody OrderRequest orderDto) {
		try {
			return orderService.createOrder(orderDto);
		} catch (Exception ex) {
			logger.error("Unexpected exception occurred.", ex);
			throw ex;
		}
	}

	@GetMapping("/getOrder/{orderId}")
	public Object getOrder(@PathVariable("orderId") Long id) {
		logger.info("Request - get order with id: ", id.toString());
		try {
			return orderValidator.validateOrderById(orderService.getOrderById(id));
		} catch (Exception ex) {
			logger.error("Unexpected exception occurred.", ex);
			throw ex;
		}
	}

	@GetMapping("/getOrdersByDate/{startDate}/{endDate}")
	public List<Order> getOrdersByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		logger.info("Request - get orders between the dates: ", startDate + " and " + "endDate");
		try {
			orderValidator.validateDates(startDate, endDate);
			return orderService.getOrdersBetweenDates(startDate, endDate);
		} catch (Exception ex) {
			logger.error("Exception occurred while creating order", ex);
			throw ex;
		}
	}

}
