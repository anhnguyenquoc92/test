package com.example.demo.queryside.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Book;

public interface BookQueryRepository extends JpaRepository<Book, Integer>{
  Book findByUid(int id);

  Book findByCode(String code);

  List<Book> findByName(String name);

  List<Book> findByCategory(String category);

  List<Book> findByAuthor(String author);
  
  List<Book> findAll();
}
