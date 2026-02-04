package org.example.inventory.service;

import org.example.inventory.dtos.request.CategoryDTO;
import org.example.inventory.dtos.respon.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryDTO categoryDTO);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
