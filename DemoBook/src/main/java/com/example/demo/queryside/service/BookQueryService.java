package com.example.demo.queryside.service;

import java.util.List;
import com.example.demo.entity.Book;

public interface BookQueryService {
  List<Book> getAllBook();

  Book findByUid(int uid);

  Book findByCode(String code);

  List<Book> findByName(String name);

  List<Book> findByCategory(String category);

  List<Book> findByAuthor(String author);
}
