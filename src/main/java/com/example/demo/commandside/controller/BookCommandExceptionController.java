package com.example.demo.commandside.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ApiError;
import com.example.demo.exception.bookexception.BookNotFoundException;
import com.example.demo.exception.bookexception.BusinessErrorCode;
import com.example.demo.exception.bookexception.DuplicateBookIdException;
import com.example.demo.exception.bookexception.DuplicateCodeException;

@ControllerAdvice
public class BookCommandExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<ApiError> bookNotFoundException(BookNotFoundException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		ApiError apiError = new ApiError("Book Not Found", details, exception.getBusinessErrorQueryCode(),
				exception.getHttpStatus());
		return new ResponseEntity<ApiError>(apiError, exception.getHttpStatus());
	}

	@ExceptionHandler(value = DuplicateBookIdException.class)
	public ResponseEntity<ApiError> duplicateBookIdException(DuplicateBookIdException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		ApiError apiError = new ApiError("Duplicate BookId Exception", details, exception.getBusinessErrorQueryCode(),
				exception.getHttpStatus());
		return new ResponseEntity<ApiError>(apiError, exception.getHttpStatus());
	}

	@ExceptionHandler(value = DuplicateCodeException.class)
	public ResponseEntity<ApiError> duplicateCodeException(DuplicateCodeException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		ApiError apiError = new ApiError("Duplicate Code Exception", details, exception.getBusinessErrorQueryCode(),
				exception.getHttpStatus());
		return new ResponseEntity<ApiError>(apiError, exception.getHttpStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ApiError apiError = new ApiError("Validation Failed", details);
		apiError.setBusinessQueryErrorCode(BusinessErrorCode.InvalidParam);
		apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}
}
