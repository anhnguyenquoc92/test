package com.example.demo.queryside.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.queryside.service.BookQueryService;

@RestController
@RequestMapping("/api/query")
public class BookQueryController {

  @Autowired
  BookQueryService bookQueryService;

  @GetMapping(value = "/books")
  public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> books = bookQueryService.getAllBook();
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/books/id/{uid}")
  public ResponseEntity<Book> findBookById(@PathVariable("uid") Integer uid) {
    Book book = bookQueryService.findByUid(uid);
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  @GetMapping(value = "/books/code/{code}")
  public ResponseEntity<Book> findBookByCode(@PathVariable("code") String code) {
    Book book = bookQueryService.findByCode(code);
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  @GetMapping(value = "/books/name/{name}")
  public ResponseEntity<List<Book>> findBookByName(@PathVariable("name") String name) {
    List<Book> books = bookQueryService.findByName(name);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/books/category/{category}")
  public ResponseEntity<List<Book>> findBookByCategory(@PathVariable("category") String category) {
    List<Book> books = bookQueryService.findByCategory(category);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/books/author/{author}")
  public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable("author") String author) {
    List<Book> books = bookQueryService.findByAuthor(author);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }
}
