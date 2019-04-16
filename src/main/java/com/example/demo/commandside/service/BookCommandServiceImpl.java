package com.example.demo.commandside.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.commandside.repository.BookCommandRepository;
import com.example.demo.entity.Book;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.utility.ValidParam;

@Service
public class BookCommandServiceImpl implements BookCommandService {
	@Autowired
	BookCommandRepository bookCommandRepository;

	@Override
	public Book createBook(Book book) {
		ValidParam.checkUniqueBookId(bookCommandRepository.getListUid(), book.getUid());
		ValidParam.checkUniqueCode(bookCommandRepository.getListCode(), book.getCode());
		ValidParam.validBookId(book.getUid());
		bookCommandRepository.save(book);
		return book;
	}

	@Override
	public Book updateBook(Book book) {
		Book outPut = bookCommandRepository.findByUid(book.getUid());
		if (outPut == null) {
			throw new BookNotFoundException("Book with id is " + book.getUid() + " Not Found");
		}
		List<String> outPuts = bookCommandRepository.getListCode();
		outPuts.remove(book.getCode());
		ValidParam.checkUniqueCode(outPuts, book.getCode());
		return bookCommandRepository.save(book);
	}

	@Override
	public int deleteBook(int uid) {
		Book book = bookCommandRepository.findByUid(uid);
		if (book == null) {
			throw new BookNotFoundException("Book with id is " + uid + " Not Found");
		}
		bookCommandRepository.delete(book);
		return uid;
	}
}
