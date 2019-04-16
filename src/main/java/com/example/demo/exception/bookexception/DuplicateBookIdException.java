package com.example.demo.exception.bookexception;

import org.springframework.http.HttpStatus;

public class DuplicateBookIdException extends BookBusinessException {
  private static final long serialVersionUID = 4826740890008323538L;

  public DuplicateBookIdException(String message) {
    super(message, BusinessErrorCode.DuplicateBookId, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
