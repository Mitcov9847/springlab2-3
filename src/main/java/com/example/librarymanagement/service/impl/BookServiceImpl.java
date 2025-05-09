package com.example.librarymanagement.service.impl;

import com.example.librarymanagement.dto.BookDto;
import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Category;
import com.example.librarymanagement.entity.Publisher;
import com.example.librarymanagement.repository.AuthorDao;
import com.example.librarymanagement.repository.BookDao;
import com.example.librarymanagement.repository.CategoryDao;
import com.example.librarymanagement.repository.PublisherDao;
import com.example.librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final CategoryDao categoryDao;

    @Override
    public BookDto createBook(BookDto dto) {
        Book book = mapToEntity(dto);
        return mapToDto(bookDao.save(book));
    }

    @Override
    public BookDto getBookById(Long id) {
        return mapToDto(bookDao.findById(id));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookDao.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(Long id, BookDto dto) {
        Book book = bookDao.findById(id);
        book.setTitle(dto.getTitle());

        Author author = authorDao.findById(dto.getAuthorId());
        Publisher publisher = publisherDao.findById(dto.getPublisherId());
        List<Category> categories = dto.getCategoryIds().stream()
                .map(categoryDao::findById)
                .collect(Collectors.toList());

        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        return mapToDto(bookDao.update(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.delete(id);
    }

    private Book mapToEntity(BookDto dto) {
        Author author = authorDao.findById(dto.getAuthorId());
        Publisher publisher = publisherDao.findById(dto.getPublisherId());
        List<Category> categories = dto.getCategoryIds().stream()
                .map(categoryDao::findById)
                .collect(Collectors.toList());

        return Book.builder()
                .title(dto.getTitle())
                .author(author)
                .publisher(publisher)
                .categories(categories)
                .build();
    }

    private BookDto mapToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorId(book.getAuthor().getId())
                .publisherId(book.getPublisher().getId())
                .categoryIds(book.getCategories().stream()
                        .map(Category::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
