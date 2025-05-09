package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Library;
import java.util.List;

public interface LibraryDao {
    Library save(Library entity);
    Library update(Library entity);
    void delete(Long id);
    Library findById(Long id);
    List<Library> findAll();
}
