package com.example.demo.cucumber.testbookcommand;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.commandside.controller.BookCommandController;
import com.example.demo.commandside.controller.BookCommandExceptionController;
import com.example.demo.commandside.repository.BookCommandRepository;
import com.example.demo.commandside.service.BookCommandServiceImpl;
import com.example.demo.entity.Book;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.exception.bookexception.DuplicateBookIdException;
import com.example.demo.exception.bookexception.DuplicateCodeException;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestBookCommandController {

	@Mock
	BookCommandRepository bookCommandRepository;

	@Mock
	BookCommandServiceImpl bookCommandService;

	@InjectMocks
	BookCommandController bookCommandController;

	MockMvc mvc;

	String json = "{\r\n" + "        \"uid\": 159,\r\n" + "        \"code\": \"1\",\r\n"
			+ "        \"name\": \"linh\",\r\n" + "        \"description\": \"1\",\r\n"
			+ "        \"category\": \"1\",\r\n" + "        \"author\": \"1\",\r\n"
			+ "        \"publisher\": \"1\",\r\n" + "        \"createUser\": \"1\",\r\n"
			+ "        \"createDate\": \"1\",\r\n" + "        \"updateUser\": \"1\",\r\n"
			+ "        \"updateDate\": \"1\"\r\n" + "    }";

	String jsonCaseInvalidParam = "{\r\n" + "        \"uid\": 159,\r\n" + "        \"code\": \"\",\r\n"
			+ "        \"name\": \"\",\r\n" + "        \"description\": \"\",\r\n" + "        \"category\": \"\",\r\n"
			+ "        \"author\": \"\",\r\n" + "        \"publisher\": \"\",\r\n" + "        \"createUser\": \"\",\r\n"
			+ "        \"createDate\": \"\",\r\n" + "        \"updateUser\": \"\",\r\n"
			+ "        \"updateDate\": \"\"\r\n" + "    }";
	Book book;

	@BeforeEach
	public void init() {
		book = new Book(1, "1", "1", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());
		mvc = MockMvcBuilders.standaloneSetup(bookCommandController)
				.setControllerAdvice(new BookCommandExceptionController()).build();
	}

	@Test
	public void testCreateBookCase1() throws Exception {
		Mockito.when(bookCommandService.createBook(book)).thenReturn(book);
		mvc.perform(post("/api/command/books/create").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	public void testCreateBookCase2() throws Exception {
		Mockito.when(bookCommandService.createBook(Mockito.any(Book.class)))
				.thenThrow(new DuplicateBookIdException("BookId should be unique"));
		mvc.perform(post("/api/command/books/create").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("message", is("Duplicate BookId Exception")));
	}

	@Test
	public void testCreateBookCase3() throws Exception {
		Mockito.when(bookCommandService.createBook(Mockito.any(Book.class)))
				.thenThrow(new DuplicateCodeException("Code should be unique"));
		mvc.perform(post("/api/command/books/create").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("message", is("Duplicate Code Exception")));
	}

	@Test
	public void testCreateBookCase4() throws Exception {
		Mockito.when(bookCommandService.createBook(book)).thenReturn(book);
		mvc.perform(
				post("/api/command/books/create").contentType(MediaType.APPLICATION_JSON).content(jsonCaseInvalidParam))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("message", is("Validation Failed")));
	}

	@Test
	public void testUpdateBookCase1() throws Exception {
		Mockito.when(bookCommandService.updateBook(book)).thenReturn(book);
		mvc.perform(put("/api/command/books/update").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateBookCase2() throws Exception {
		Mockito.when(bookCommandService.updateBook(Mockito.any(Book.class)))
				.thenThrow(new DuplicateBookIdException("BookId should be unique"));
		mvc.perform(put("/api/command/books/update").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("message", is("Duplicate BookId Exception")));
	}

	@Test
	public void testDeleteBookCase1() throws Exception {
		Mockito.when(bookCommandService.deleteBook(book.getUid())).thenReturn(book.getUid());
		mvc.perform(delete("/api/command/books/delete/{uid}", book.getUid()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteBookCase2() throws Exception {
		Mockito.when(bookCommandService.deleteBook(1)).thenThrow(new BookNotFoundException("Book not found"));
		mvc.perform(delete("/api/command/books/delete/{uid}", book.getUid()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("message", is("Book Not Found")));
	}
}
