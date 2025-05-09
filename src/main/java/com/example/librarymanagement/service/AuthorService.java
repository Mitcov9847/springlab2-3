package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto getAuthorById(Long id);
    List<AuthorDto> getAllAuthors();
    AuthorDto updateAuthor(Long id, AuthorDto authorDto);
    void deleteAuthor(Long id);
}
