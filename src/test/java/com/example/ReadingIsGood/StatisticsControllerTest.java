package com.example.ReadingIsGood;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ReadingIsGood.controller.StatisticsController;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.repository.CustomerRepository;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.repository.StatisticsRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.CustomerService;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.service.StatisticsService;
import com.example.ReadingIsGood.service.StatisticsServiceImpl;
import com.google.gson.Gson;

@WebMvcTest(value = StatisticsController.class)
public class StatisticsControllerTest {

	@MockBean
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private BookService bookService;

	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private StatisticsRepository statisticsRepository;

	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private StatisticsServiceImpl statisticsServiceImpl;

	@Autowired
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
	public void getMonthlyStatisticTest() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		Book book = new Book();
		book.setBookId(1L);
		book.setStock(100);
		this.mockMvc.perform(
				get("/statistics/getMonthlyStatistic/"+customer.getCustomerId().toString()).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(book)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
