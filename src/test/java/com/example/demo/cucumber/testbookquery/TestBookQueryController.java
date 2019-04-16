package com.example.demo.cucumber.testbookquery;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.entity.Book;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.queryside.controller.BookQueryController;
import com.example.demo.queryside.controller.BookQueryExceptionController;
import com.example.demo.queryside.service.BookQueryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class TestBookQueryController {

	@Mock
	BookQueryService bookQueryService;

	@InjectMocks
	BookQueryController bookQueryController;

	MockMvc mvc;

	public List<Book> booksCasePass = new ArrayList<Book>();
	public List<Book> booksCaseFail = new ArrayList<Book>();
	Book book1 = new Book(1, "1", "linh", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());
	Book book2 = new Book(2, "1", "linh", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());

	@BeforeEach
	public void init() {
		booksCasePass.add(book1);
		booksCasePass.add(book2);
		mvc = MockMvcBuilders.standaloneSetup(bookQueryController)
				.setControllerAdvice(new BookQueryExceptionController()).build();
	}

	@Test
	public void testGetAllBooksCase1() throws Exception {
		Mockito.when(bookQueryService.getAllBook()).thenReturn(booksCasePass);
		mvc.perform(get("/api/query/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("linh")));
	}

	@Test
	public void testGetAllBooksCase2() throws Exception {
		Mockito.when(bookQueryService.getAllBook()).thenThrow(new BookNotFoundException("Don't have any book"));
		mvc.perform(get("/api/query/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("message", is("Book Not Found")));
	}

	@Test
	public void testFindBookByIdCase1() throws Exception {
		Mockito.when(bookQueryService.findByUid(1)).thenReturn(book1);
		mvc.perform(get("/api/query/books/id/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("name", is("linh")));
	}

	@Test
	public void testFindBookByIdCase2() throws Exception {
		Mockito.when(bookQueryService.findByUid(1)).thenThrow(new BookNotFoundException("Don't have any book"));
		mvc.perform(get("/api/query/books/id/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("message", is("Book Not Found")));
	}

	@Test
	public void testFindBookByCodeCase1() throws Exception {
		Mockito.when(bookQueryService.findByCode("1")).thenReturn(book1);
		mvc.perform(get("/api/query/books/code/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("name", is("linh")));
	}

	@Test
	public void testFindBookByCodeCase2() throws Exception {
		Mockito.when(bookQueryService.findByCode("1")).thenThrow(new BookNotFoundException("Don't have any book"));
		mvc.perform(get("/api/query/books/code/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("message", is("Book Not Found")));
	}

	@Test
	public void testFindBookByNameCase1() throws Exception {
		Mockito.when(bookQueryService.findByName("linh")).thenReturn(booksCasePass);
		mvc.perform(get("/api/query/books/name/linh").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("linh")));
	}

	@Test
	public void testFindBookByNameCase2() throws Exception {
		Mockito.when(bookQueryService.findByName("linh")).thenThrow(new BookNotFoundException("Don't have any book"));
		mvc.perform(get("/api/query/books/name/linh").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("message", is("Book Not Found")));
	}

	@Test
	public void testFindBookByCategoryCase1() throws Exception {
		Mockito.when(bookQueryService.findByCategory("1")).thenReturn(booksCasePass);
		mvc.perform(get("/api/query/books/category/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("linh")));
	}

	@Test
	public void testFindBookByCategoryCase2() throws Exception {
		Mockito.when(bookQueryService.findByCategory("1")).thenThrow(new BookNotFoundException("Don't have any book"));
		mvc.perform(get("/api/query/books/category/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("message", is("Book Not Found")));
	}

	@Test
	public void testFindBookByAuthorCase1() throws Exception {
		Mockito.when(bookQueryService.findByAuthor("1")).thenReturn(booksCasePass);
		mvc.perform(get("/api/query/books/author/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("linh")));
	}

	@Test
	public void testFindBookByAuthorCase2() throws Exception {
		Mockito.when(bookQueryService.findByAuthor("1")).thenThrow(new BookNotFoundException("Don't have any book"));
		mvc.perform(get("/api/query/books/author/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("message", is("Book Not Found")));
	}
}
