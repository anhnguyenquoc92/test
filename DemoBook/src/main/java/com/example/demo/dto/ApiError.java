package com.example.demo.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.bookexception.BusinessErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	String message;
	List<String> details;
	BusinessErrorCode businessQueryErrorCode;
	HttpStatus httpStatus;
}
