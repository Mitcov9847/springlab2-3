package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Book;
import java.util.List;

public interface BookDao {
    Book save(Book entity);
    Book update(Book entity);
    void delete(Long id);
    Book findById(Long id);
    List<Book> findAll();
}
