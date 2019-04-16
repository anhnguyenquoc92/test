package com.example.demo.exception.bookexception;

public enum BusinessErrorCode {
  BookNotFound(40404),
  InvalidParam(40000),
  InteralServer(500000),
  DuplicateCode(500001),
  DuplicateBookId(500002);

  private final int id;

  BusinessErrorCode(int id) {
    this.id = id;
  }

  public int getValue() {
    return id;
  }
}
