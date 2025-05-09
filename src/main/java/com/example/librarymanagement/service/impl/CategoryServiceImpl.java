package com.example.librarymanagement.service.impl;

import com.example.librarymanagement.dto.CategoryDto;
import com.example.librarymanagement.entity.Category;
import com.example.librarymanagement.repository.CategoryDao;
import com.example.librarymanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category category = Category.builder().name(dto.getName()).build();
        return mapToDto(categoryDao.save(category));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return mapToDto(categoryDao.findById(id));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDao.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category = categoryDao.findById(id);
        category.setName(dto.getName());
        return mapToDto(categoryDao.update(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryDao.delete(id);
    }

    private CategoryDto mapToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
