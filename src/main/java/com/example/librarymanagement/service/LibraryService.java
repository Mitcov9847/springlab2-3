package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.LibraryDto;

import java.util.List;

public interface LibraryService {
    LibraryDto createLibrary(LibraryDto dto);
    LibraryDto getLibraryById(Long id);
    List<LibraryDto> getAllLibraries();
    LibraryDto updateLibrary(Long id, LibraryDto dto);
    void deleteLibrary(Long id);
}
