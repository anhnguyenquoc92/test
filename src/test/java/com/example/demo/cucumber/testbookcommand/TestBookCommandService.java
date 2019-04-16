package com.example.demo.cucumber.testbookcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.commandside.repository.BookCommandRepository;
import com.example.demo.commandside.service.BookCommandServiceImpl;
import com.example.demo.entity.Book;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.exception.bookexception.DuplicateBookIdException;
import com.example.demo.exception.bookexception.DuplicateCodeException;
import com.example.demo.exception.bookexception.ValidParamException;
import com.example.demo.queryside.repository.BookQueryRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class TestBookCommandService {
	@Mock
	BookCommandRepository mockBookCommandRepository;

	@InjectMocks
	BookCommandServiceImpl bookCommandServiceImpl;

	Book book1 = new Book(1, "1", "1", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());
	Book book2 = new Book(-1, "1", "1", "1", "1", "1", "1", "1", ZonedDateTime.now(), "1", ZonedDateTime.now());
	
	@Test
	public void testCreatBookCase1() {
		Mockito.when(mockBookCommandRepository.save(book1)).thenReturn(book1);
		assertEquals(bookCommandServiceImpl.createBook(book1), book1);
	}

	@Test
	public void testCreatBookCase2() {
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		Mockito.when(mockBookCommandRepository.getListUid()).thenReturn(integers);
		Mockito.when(mockBookCommandRepository.save(book1)).thenReturn(book1);
		DuplicateBookIdException exception = assertThrows(DuplicateBookIdException.class,
				() -> bookCommandServiceImpl.createBook(book1));
		assertEquals(exception.getMessage(), "BookId should be unique");
	}

	@Test
	public void testCreateBookCase3() {
		List<String> strings = new ArrayList<>();
		strings.add("1");
		Mockito.when(mockBookCommandRepository.getListCode()).thenReturn(strings);
		Mockito.when(mockBookCommandRepository.save(book1)).thenReturn(book1);
		DuplicateCodeException exception = assertThrows(DuplicateCodeException.class,
				() -> bookCommandServiceImpl.createBook(book1));
		assertEquals(exception.getMessage(), "Code should be unique");
	}

	@Test
	public void testCreateBookCase4() {
		Mockito.when(mockBookCommandRepository.save(book2)).thenReturn(book2);
		ValidParamException exception = assertThrows(ValidParamException.class,
				() -> bookCommandServiceImpl.createBook(book2));
		assertEquals(exception.getMessage(), "Book id should be greater than 1");
	}

	@Test
	public void testUpdateBookCase1() {
		Mockito.when(mockBookCommandRepository.findByUid(1)).thenReturn(book1);
		Mockito.when(mockBookCommandRepository.save(book1)).thenReturn(book1);
		assertEquals(bookCommandServiceImpl.updateBook(book1), book1);
	}

	@Test
	void testUpdateBookCase2() {
		Mockito.when(mockBookCommandRepository.findByUid(1)).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookCommandServiceImpl.updateBook(book1));
		assertEquals(exception.getMessage(), "Book with id is " + book1.getUid() + " Not Found");
	}

	@Test
	void testDeleteBookCase1() {
		Mockito.when(mockBookCommandRepository.findByUid(1)).thenReturn(book1);
		assertEquals(bookCommandServiceImpl.deleteBook(1), book1.getUid());
	}

	@Test
	void testDeleteBookCase2() {
		Mockito.when(mockBookCommandRepository.findByUid(1)).thenReturn(null);
		BookNotFoundException exception = assertThrows(BookNotFoundException.class,
				() -> bookCommandServiceImpl.deleteBook(1));
		assertEquals(exception.getMessage(), "Book with id is 1 Not Found");
	}
}
