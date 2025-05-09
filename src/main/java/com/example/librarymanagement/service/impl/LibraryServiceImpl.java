package com.example.librarymanagement.service.impl;

import com.example.librarymanagement.dto.LibraryDto;
import com.example.librarymanagement.entity.Library;
import com.example.librarymanagement.repository.LibraryDao;
import com.example.librarymanagement.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryDao libraryDao;

    @Override
    public LibraryDto createLibrary(LibraryDto dto) {
        Library library = Library.builder()
                .location(dto.getLocation())
                .build();
        return mapToDto(libraryDao.save(library));
    }

    @Override
    public LibraryDto getLibraryById(Long id) {
        return mapToDto(libraryDao.findById(id));
    }

    @Override
    public List<LibraryDto> getAllLibraries() {
        return libraryDao.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryDto updateLibrary(Long id, LibraryDto dto) {
        Library library = libraryDao.findById(id);
        library.setLocation(dto.getLocation());
        return mapToDto(libraryDao.update(library));
    }

    @Override
    public void deleteLibrary(Long id) {
        libraryDao.delete(id);
    }

    private LibraryDto mapToDto(Library library) {
        return LibraryDto.builder()
                .id(library.getId())
                .location(library.getLocation())
                .build();
    }
}
