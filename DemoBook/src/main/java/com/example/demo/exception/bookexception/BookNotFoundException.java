package com.example.demo.exception.bookexception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BookBusinessException {
  private static final long serialVersionUID = -3520685461510855926L;

  public BookNotFoundException(String message) {
    super(message, BusinessErrorCode.BookNotFound, HttpStatus.NOT_FOUND);
  }
}
