package com.example.librarymanagement.service.impl;

import com.example.librarymanagement.dto.AuthorDto;
import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.repository.AuthorDao;
import com.example.librarymanagement.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = Author.builder()
                .name(authorDto.getName())
                .build();
        Author saved = authorDao.save(author);
        return mapToDto(saved);
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorDao.findById(id);
        return mapToDto(author);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorDao.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto dto) {
        Author existing = authorDao.findById(id);
        existing.setName(dto.getName());
        Author updated = authorDao.update(existing);
        return mapToDto(updated);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorDao.delete(id);
    }

    private AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }
}
