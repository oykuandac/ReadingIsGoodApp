package com.example.ReadingIsGood.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.entity.OrderBookMap;
import com.example.ReadingIsGood.model.OrderRequest;
import com.example.ReadingIsGood.model.OrderResponse;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.validator.BookValidator;
import com.example.ReadingIsGood.validator.CustomerValidator;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private CustomerService customerService;
	private BookService bookService;
	private BookValidator bookValidator;
	private CustomerValidator customerValidator;

	public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, BookService bookService,
			BookValidator bookValidator, CustomerValidator customerValidator) {
		this.orderRepository = orderRepository;
		this.customerService = customerService;
		this.bookService = bookService;
		this.bookValidator = bookValidator;
		this.customerValidator = customerValidator;
	}

	@Override
	public OrderResponse createOrder(OrderRequest request) {
		Customer customer = customerValidator
				.validateCustomer(customerService.getCustomerById(request.getCustomerId()));
		Order order = createNewOrder(request, customer);
		orderRepository.save(order); // order is saved, now we have orderId
		Order orderToBeUpdated = createOrderToBeUpdated(request, order); // set order with orderBookMap
		orderRepository.save(orderToBeUpdated); // update order
		return createOrderResponse(order);
	}

	@Override
	public Object getOrderById(Long id) {
		return orderRepository.findByOrderId(id);
	}

	@Override
	public List<Order> getOrdersBetweenDates(Date startDate, Date endDate) {
		return orderRepository.findByCreatedAtBetween(startDate, endDate);
	}

	private Order createNewOrder(OrderRequest request, Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setCreatedAt(new Date());
		return order;
	}

	private Order createOrderToBeUpdated(OrderRequest orderRequest, Order order) {
		List<OrderBookMap> orderBookMap = new ArrayList<>();
		for (Entry<Long, Integer> bookMap : orderRequest.getBookIdAndAmount().entrySet()) {
			Book book = bookService.getBookById(bookMap.getKey());
			bookValidator.validateBook(bookMap, book); // validation for book
			book.setStock(book.getStock() - bookMap.getValue());
			book.setUpdateDateTime(new Date());
			bookService.saveBook(book); // update book stock
			OrderBookMap map = new OrderBookMap();
			map.setBook(book);
			map.setNumberOfBook(bookMap.getValue());
			map.setOrder(order);
			orderBookMap.add(map);
		}
		order.setOrderBookMap(orderBookMap);
		return order;
	}

	private OrderResponse createOrderResponse(Order order) {
		List<Book> books = new ArrayList<>();
		OrderResponse response = new OrderResponse();
		order.getOrderBookMap().stream().forEach(obj -> books.add(obj.getBook()));
		response.setCustomer(order.getCustomer());
		response.setOrderId(order.getOrderId());
		response.setOrderedBooks(books);
		return response;
	}

}
