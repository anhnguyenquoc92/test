package com.example.demo.queryside.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.queryside.repository.BookQueryRepository;
import com.example.demo.utility.ValidParam;

@Service
public class BookQueryServiceImpl implements BookQueryService {
  @Autowired
  BookQueryRepository bookQueryRepository;

  @Override
  public List<Book> getAllBook() {
    List<Book> outPut = null;
    outPut = bookQueryRepository.findAll();
    if (outPut.isEmpty()) {
      throw new BookNotFoundException("Don't have any book");
    }
    return outPut;
  }

  @Override
  public Book findByUid(int uid) {
    ValidParam.validBookId(uid);
    Book outPut = null;
    outPut = bookQueryRepository.findByUid(uid);
    if (outPut == null) {
      throw new BookNotFoundException("Book with id is " + uid + " Not Found");
    }
    return outPut;
  }

  @Override
  public Book findByCode(String code) {
    Book outPut = null;
    outPut = bookQueryRepository.findByCode(code);
    if (outPut == null) {
      throw new BookNotFoundException("Book with code is " + code + " Not Found");
    }
    return outPut;
  }

  @Override
  public List<Book> findByName(String name) {
    List<Book> outPuts = null;
    outPuts = (List<Book>) bookQueryRepository.findByName(name);
    if (outPuts.isEmpty()) {
      throw new BookNotFoundException("Book with name is " + name + " Not Found");
    }
    return outPuts;
  }

  @Override
  public List<Book> findByCategory(String category) {
    List<Book> outPuts = null;
    outPuts = (List<Book>) bookQueryRepository.findByCategory(category);
    if (outPuts.isEmpty()) {
      throw new BookNotFoundException("Book with category is " + category + " Not Found");
    }
    return outPuts;
  }

  @Override
  public List<Book> findByAuthor(String author) {
    List<Book> outPuts = null;
    outPuts = (List<Book>) bookQueryRepository.findByAuthor(author);
    if (outPuts.isEmpty()) {
      throw new BookNotFoundException("Book with author is " + author + " Not Found");
    }
    return outPuts;
  }
}
