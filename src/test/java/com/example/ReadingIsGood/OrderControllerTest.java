package com.example.ReadingIsGood;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.example.ReadingIsGood.controller.BookController;
import com.example.ReadingIsGood.controller.CustomerController;
import com.example.ReadingIsGood.controller.OrderController;
import com.example.ReadingIsGood.controller.StatisticsController;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.model.OrderRequest;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.repository.CustomerRepository;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.repository.StatisticsRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.CustomerService;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.service.OrderServiceImpl;
import com.example.ReadingIsGood.service.StatisticsService;
import com.google.gson.Gson;

@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private BookController bookController;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private CustomerController customerController;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private BookService bookService;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private StatisticsRepository statisticsRepository;

	@MockBean
	private StatisticsService statisticsService;

	@MockBean
	private StatisticsController statisticsController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Gson gson;

	private MockMvc mockMvc;

	@BeforeEach()
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createOrderTest() throws Exception {
		OrderRequest dto = new OrderRequest();
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		dto.setCustomerId(1L);
		Map<Long, Integer> map = new HashMap<>();
		map.put(1L, 1);
		dto.setBookIdAndAmount(map);
		Book book = new Book();
		book.setBookId(1L);
		book.setStock(100);
		Mockito.when(customerService.getCustomerById(Mockito.any())).thenReturn(customer);
		Mockito.when(bookService.getBookById(Mockito.any())).thenReturn(book);
		this.mockMvc
				.perform(post("/order/createOrder").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(dto)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void createOrderTestOutOfStock() throws Exception {
		OrderRequest dto = new OrderRequest();
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		dto.setCustomerId(1L);
		Map<Long, Integer> map = new HashMap<>();
		map.put(1L, 1);
		dto.setBookIdAndAmount(map);
		Book book = new Book();
		Mockito.when(customerService.getCustomerById(Mockito.any())).thenReturn(customer);
		Mockito.when(bookService.getBookById(Mockito.any())).thenReturn(book);
		this.mockMvc
				.perform(post("/order/createOrder").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void createOrderTestBookIsNull() throws Exception {
		OrderRequest dto = new OrderRequest();
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		dto.setCustomerId(1L);
		Map<Long, Integer> map = new HashMap<>();
		map.put(1L, 1);
		dto.setBookIdAndAmount(map);
		Mockito.when(customerService.getCustomerById(Mockito.any())).thenReturn(customer);
		Mockito.when(bookService.getBookById(Mockito.any())).thenReturn(null);
		this.mockMvc
				.perform(post("/order/createOrder").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void createOrderTestInvalidBookAmount() throws Exception {
		OrderRequest dto = new OrderRequest();
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		dto.setCustomerId(1L);
		Map<Long, Integer> map = new HashMap<>();
		map.put(1L, -1);
		dto.setBookIdAndAmount(map);
		Book book = new Book();
		book.setBookId(1L);
		book.setStock(100);
		Mockito.when(customerService.getCustomerById(Mockito.any())).thenReturn(customer);
		Mockito.when(bookService.getBookById(Mockito.any())).thenReturn(book);
		Assertions.assertThrows(NestedServletException.class, () -> this.mockMvc
				.perform(post("/order/createOrder").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(dto))));
	}
	
	@Test
	public void createOrderTestCustomerIsNull() throws Exception {
		OrderRequest dto = new OrderRequest();
		Map<Long, Integer> map = new HashMap<>();
		map.put(1L, 1);
		dto.setBookIdAndAmount(map);
		Book book = new Book();
		book.setBookId(1L);
		book.setStock(100);
		Mockito.when(customerService.getCustomerById(Mockito.any())).thenReturn(null);
		Mockito.when(bookService.getBookById(Mockito.any())).thenReturn(book);
		this.mockMvc
				.perform(post("/order/createOrder").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void getOrderTest() throws Exception {
		Order order = new Order();
		order.setOrderId(1L);
		Mockito.when(orderService.getOrderById(order.getOrderId())).thenReturn(order);
		this.mockMvc
				.perform(
						get("/order/getOrder/" + order.getOrderId().toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void getOrderTestWithException() throws Exception {
		Order order = new Order();
		order.setOrderId(1L);
		this.mockMvc
				.perform(
						get("/order/getOrder/" + order.getOrderId().toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	public void getOrderTestWithNullOrder() throws Exception {
		this.mockMvc.perform(get("/order/getOrder/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	public void getOrdersByDateTest() throws Exception {
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		Date now = new Date();
		order.setOrderId(1L);
		order.setCreatedAt(now);
		orders.add(order);
		Mockito.when(orderService.getOrdersBetweenDates(Mockito.any(), Mockito.any())).thenReturn(orders);
		this.mockMvc
				.perform(get("/order/getOrdersByDate/2022-01-01/2030-12-31").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void getOrdersByDatesNotValidTest() throws Exception {
		Order order = new Order();
		Date now = new Date();
		order.setOrderId(1L);
		order.setCreatedAt(now);
		Mockito.when(orderService.getOrderById(order.getOrderId())).thenReturn(order);
		this.mockMvc
				.perform(get("/order/getOrdersByDate/2030-01-01/2022-12-31").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

}
