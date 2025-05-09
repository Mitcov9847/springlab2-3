package com.example.librarymanagement.service.impl;

import com.example.librarymanagement.dto.PublisherDto;
import com.example.librarymanagement.entity.Publisher;
import com.example.librarymanagement.repository.PublisherDao;
import com.example.librarymanagement.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherDao publisherDao;

    @Override
    public PublisherDto createPublisher(PublisherDto dto) {
        Publisher publisher = Publisher.builder()
                .name(dto.getName())
                .build();
        return mapToDto(publisherDao.save(publisher));
    }

    @Override
    public PublisherDto getPublisherById(Long id) {
        return mapToDto(publisherDao.findById(id));
    }

    @Override
    public List<PublisherDto> getAllPublishers() {
        return publisherDao.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDto updatePublisher(Long id, PublisherDto dto) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(dto.getName());
        return mapToDto(publisherDao.update(publisher));
    }

    @Override
    public void deletePublisher(Long id) {
        publisherDao.delete(id);
    }

    private PublisherDto mapToDto(Publisher publisher) {
        return PublisherDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }
}
