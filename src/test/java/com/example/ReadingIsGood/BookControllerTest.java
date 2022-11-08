package com.example.ReadingIsGood;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Assertions;
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
import org.springframework.web.util.NestedServletException;

import com.example.ReadingIsGood.controller.BookController;
import com.example.ReadingIsGood.controller.StatisticsController;
import com.example.ReadingIsGood.entity.Book;
import com.example.ReadingIsGood.repository.BookRepository;
import com.example.ReadingIsGood.repository.CustomerRepository;
import com.example.ReadingIsGood.repository.OrderRepository;
import com.example.ReadingIsGood.repository.StatisticsRepository;
import com.example.ReadingIsGood.service.BookService;
import com.example.ReadingIsGood.service.CustomerService;
import com.example.ReadingIsGood.service.OrderService;
import com.example.ReadingIsGood.service.StatisticsService;
import com.google.gson.Gson;

@WebMvcTest(value = BookController.class)
public class BookControllerTest {

	@MockBean
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private CustomerService customerService;

	@Autowired
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
	public void createBookTest() throws Exception {
		this.mockMvc.perform(
				post("/book/createBook").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(new Book())))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void createBookTestInvalidBookPrice() throws Exception {
		Book book = new Book();
		book.setPrice(-1);
		Assertions.assertThrows(NestedServletException.class, () -> this.mockMvc
				.perform(post("/book/createBook").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(book))));
	}

	@Test
	public void createBookTestInvalidBookStock() throws Exception {
		Book book = new Book();
		book.setStock(-1);
		Assertions.assertThrows(NestedServletException.class, () -> this.mockMvc
				.perform(post("/book/createBook").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(book))));
	}
	
	@Test
	public void createBookTestBookIsNull() throws Exception {
		this.mockMvc.perform(
				post("/book/createBook").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(null)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
