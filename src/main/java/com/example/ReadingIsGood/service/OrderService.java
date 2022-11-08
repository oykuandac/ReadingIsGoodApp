package com.example.ReadingIsGood.service;

import java.util.Date;
import java.util.List;

import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.model.OrderRequest;
import com.example.ReadingIsGood.model.OrderResponse;

public interface OrderService {

	public OrderResponse createOrder(OrderRequest orderRequest);

	public Object getOrderById(Long id);

	public List<Order> getOrdersBetweenDates(Date startDate, Date endDate);
}
