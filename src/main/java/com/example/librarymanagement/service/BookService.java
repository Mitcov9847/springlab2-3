package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto dto);
    BookDto getBookById(Long id);
    List<BookDto> getAllBooks();
    BookDto updateBook(Long id, BookDto dto);
    void deleteBook(Long id);
}
