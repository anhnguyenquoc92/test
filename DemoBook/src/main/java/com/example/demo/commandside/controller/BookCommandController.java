	 package com.example.demo.commandside.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commandside.service.BookCommandService;
import com.example.demo.entity.Book;

@RestController
@RequestMapping("/api/command")
public class BookCommandController {
	@Autowired
	BookCommandService bookCommandService;

	@PostMapping(value = "/books/create")
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		Book outPut = bookCommandService.createBook(book);
		return new ResponseEntity<Book>(outPut, HttpStatus.CREATED);
	}

	@PutMapping(value = "/books/update")
	public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
		Book outPut = bookCommandService.updateBook(book);
		return new ResponseEntity<Book>(outPut, HttpStatus.OK);
	}

	@DeleteMapping(value = "/books/delete/{uid}")
	public ResponseEntity<Void> deleteBook(@PathVariable("uid") Integer uid) {
		bookCommandService.deleteBook(uid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
