package com.example.demo.exception.bookexception;

import org.springframework.http.HttpStatus;

public class DuplicateCodeException extends BookBusinessException {
  private static final long serialVersionUID = -6309685693063362452L;

  public DuplicateCodeException(String message) {
    super(message, BusinessErrorCode.DuplicateCode, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
