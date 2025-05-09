package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Author;
import java.util.List;

public interface AuthorDao {
    Author save(Author entity);
    Author update(Author entity);
    void delete(Long id);
    Author findById(Long id);
    List<Author> findAll();
}
