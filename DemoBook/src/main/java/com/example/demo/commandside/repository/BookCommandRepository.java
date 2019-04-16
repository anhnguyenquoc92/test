package com.example.demo.commandside.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.Book;

public interface BookCommandRepository extends JpaRepository<Book, Integer>{

  @Query("SELECT b.uid FROM Book b")
  List<Integer> getListUid();
  
  @Query("SELECT b.code FROM Book b")
  List<String> getListCode();
  
  Book findByUid(int id);

  Book findByCode(String code);
}
