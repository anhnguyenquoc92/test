package com.example.demo.commandside.service;

import com.example.demo.entity.Book;

public interface BookCommandService {
  Book createBook(Book book);
  Book updateBook(Book book);
  int deleteBook(int uid);
}
