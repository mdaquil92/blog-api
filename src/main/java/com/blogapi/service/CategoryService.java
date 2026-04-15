package com.blogapi.service;

import com.blogapi.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto, Long id);
    void deleteCategory(Long id);
}
