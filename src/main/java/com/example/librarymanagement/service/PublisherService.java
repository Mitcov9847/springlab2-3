package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    PublisherDto createPublisher(PublisherDto publisherDto);
    PublisherDto getPublisherById(Long id);
    List<PublisherDto> getAllPublishers();
    PublisherDto updatePublisher(Long id, PublisherDto publisherDto);
    void deletePublisher(Long id);
}
