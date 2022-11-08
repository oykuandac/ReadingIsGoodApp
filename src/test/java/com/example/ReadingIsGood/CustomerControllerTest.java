package com.example.ReadingIsGood;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

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

import com.example.ReadingIsGood.controller.BookController;
import com.example.ReadingIsGood.controller.CustomerController;
import com.example.ReadingIsGood.controller.StatisticsController;
import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.repository.CustomerRepository;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.repository.StatisticsRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.CustomerService;
import com.example.ReadingIsGood.service.CustomerServiceImpl;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.service.StatisticsService;
import com.example.ReadingIsGood.validator.CustomerValidator;
import com.google.gson.Gson;

@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	@MockBean
	private OrderService orderService;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	private CustomerValidator customerValidator;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private BookController bookController;

	@MockBean
	private BookRepository bookRepository;

	@Autowired
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
	public void createCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setEmail("customer@gmail.com");
		this.mockMvc.perform(
				post("/customer/createCustomer").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(customer)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void createCustomerTestInvalidEmail() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setEmail("customer");
		this.mockMvc.perform(
				post("/customer/createCustomer").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(customer)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	@Test
	public void createCustomerTestEmailExists() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setEmail("customer@gmail.com");
		Mockito.when(customerService.getCustomerByEmail(customer.getEmail())).thenReturn(customer);
		this.mockMvc.perform(
				post("/customer/createCustomer").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(customer)))
				.andExpect(MockMvcResultMatchers.status().isConflict());

	}

	@Test
	public void listAllOrdersOfCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		List<Order> orders = new ArrayList<>();
		Mockito.when(customerService.listAllOrdersOfCustomerId(customer.getCustomerId(), 1, 1)).thenReturn(orders);
		this.mockMvc.perform(get("/customer/listOrdersOfCustomer/" + customer.getCustomerId().toString() + "/1" + "/1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void listAllOrdersOfCustomerTest2() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		List<Order> orders = new ArrayList<>();
		Mockito.when(customerService.listAllOrdersOfCustomerId(customer.getCustomerId(), 1, 1)).thenReturn(orders);
		this.mockMvc.perform(get("/customer/listOrdersOfCustomer/" + customer.getCustomerId().toString() + "/0" + "/0")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
}
