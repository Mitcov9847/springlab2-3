package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Publisher;
import java.util.List;

public interface PublisherDao {
    Publisher save(Publisher entity);
    Publisher update(Publisher entity);
    void delete(Long id);
    Publisher findById(Long id);
    List<Publisher> findAll();
}
