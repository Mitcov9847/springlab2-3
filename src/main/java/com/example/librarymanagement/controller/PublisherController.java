package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.PublisherDto;
import com.example.librarymanagement.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public PublisherDto create(@RequestBody PublisherDto dto) {
        return publisherService.createPublisher(dto);
    }

    @GetMapping("/{id}")
    public PublisherDto getById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @GetMapping
    public List<PublisherDto> getAll() {
        return publisherService.getAllPublishers();
    }

    @PutMapping("/{id}")
    public PublisherDto update(@PathVariable Long id, @RequestBody PublisherDto dto) {
        return publisherService.updatePublisher(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
}
