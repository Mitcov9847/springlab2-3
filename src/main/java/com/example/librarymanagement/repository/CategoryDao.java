package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Category;
import java.util.List;

public interface CategoryDao {
    Category save(Category entity);
    Category update(Category entity);
    void delete(Long id);
    Category findById(Long id);
    List<Category> findAll();
}

