package com.example.demo.cucumber.testbookquery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Book;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.exception.bookexception.ValidParamException;
import com.example.demo.queryside.repository.BookQueryRepository;
import com.example.demo.queryside.service.BookQueryServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestBookQueryService {

	@Mock
	BookQueryRepository bookQueryRepository;

	@InjectMocks
	BookQueryServiceImpl bookQueryServiceImpl;

	public List<Book> booksCasePass = new ArrayList<Book>();
	public List<Book> booksCaseFail = new ArrayList<Book>();
	Book book1 = new Book(1, "1", "1", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());
	Book book2 = new Book(2, "1", "1", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());

	@BeforeEach
	public void init() {
		booksCasePass.add(book1);
		booksCasePass.add(book2);
	}

	@Test
	public void testGetAllBookCase1() {
		Mockito.when(bookQueryRepository.findAll()).thenReturn(booksCasePass);
		assertEquals(bookQueryServiceImpl.getAllBook().size(), 2);
	}

	@Test
	public void testGetAllBookCase2() {
		Mockito.when(bookQueryRepository.findAll()).thenReturn(booksCaseFail);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.getAllBook());
		assertEquals(exception.getMessage(), "Don't have any book");
	}

	@Test
	public void testFindByUidCase1() {
		Mockito.when(bookQueryRepository.findByUid(1)).thenReturn(book1);
		assertEquals(bookQueryServiceImpl.findByUid(1).getUid(), 1);
	}

	@Test
	public void testFindByUidCase2() {
		Mockito.when(bookQueryRepository.findByUid(2)).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByUid(2));
		assertEquals(exception.getMessage(), "Book with id is 2 Not Found");
	}

	@Test
	public void testFindByUidCase3() {
		ValidParamException exception = assertThrows(ValidParamException.class,
				() -> bookQueryServiceImpl.findByUid(-1));
		assertEquals(exception.getMessage(), "Book id should be greater than 1");
	}

	@Test
	public void testFindByCodeCase1() {
		Mockito.when(bookQueryRepository.findByCode("1")).thenReturn(book1);
		assertEquals(bookQueryServiceImpl.findByCode("1").getCode(), "1");
	}

	@Test
	public void testFindByCodeCase2() {
		Mockito.when(bookQueryRepository.findByCode("1")).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByCode("1"));
		assertEquals(exception.getMessage(), "Book with code is 1 Not Found");
	}

	@Test
	public void testFindByNameCase1() {
		Mockito.when(bookQueryRepository.findByName("1")).thenReturn(booksCasePass);
		assertEquals(bookQueryServiceImpl.findByName("1").size(), 2);
	}

	@Test
	public void testFindByNameCase2() {
		Mockito.when(bookQueryRepository.findByName("1")).thenReturn(booksCaseFail);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByName("1"));
		assertEquals(exception.getMessage(), "Book with name is 1 Not Found");
	}

	@Test
	public void testFindByCategoryCase1() {
		Mockito.when(bookQueryRepository.findByCategory("1")).thenReturn(booksCasePass);
		assertEquals(bookQueryServiceImpl.findByCategory("1").size(), 2);
	}

	@Test
	public void testFindByCategoryCase2() {
		Mockito.when(bookQueryRepository.findByCategory("1")).thenReturn(booksCaseFail);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByCategory("1"));
		assertEquals(exception.getMessage(), "Book with category is 1 Not Found");
	}

	@Test
	public void testFindByAuthorCase1() {
		Mockito.when(bookQueryRepository.findByAuthor("1")).thenReturn(booksCasePass);
		assertEquals(bookQueryServiceImpl.findByAuthor("1").size(), 2);
	}

	@Test
	public void testFindByAuthorCase2() {
		Mockito.when(bookQueryRepository.findByAuthor("1")).thenReturn(booksCaseFail);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookQueryServiceImpl.findByAuthor("1"));
		assertEquals(exception.getMessage(), "Book with author is 1 Not Found");
	}
}
