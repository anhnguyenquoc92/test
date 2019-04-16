package com.example.demo.exception.bookexception;

import org.springframework.http.HttpStatus;

public class ValidParamException extends BookBusinessException {
  private static final long serialVersionUID = 1204611003703222877L;

  public ValidParamException(String message) {
    super(message,BusinessErrorCode.InvalidParam,HttpStatus.BAD_REQUEST);
  }
}
