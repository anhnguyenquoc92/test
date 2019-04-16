package com.example.demo.queryside.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ApiError;
import com.example.demo.exception.bookexception.BookNotFoundException;

@ControllerAdvice
public class BookQueryExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<ApiError> bookNotFoundException(BookNotFoundException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		ApiError apiError = new ApiError("Book Not Found", details, exception.getBusinessErrorQueryCode(),
				exception.getHttpStatus());
		return new ResponseEntity<ApiError>(apiError, exception.getHttpStatus());
	}
}
