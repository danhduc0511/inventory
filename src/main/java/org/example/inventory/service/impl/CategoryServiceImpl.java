package org.example.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.CategoryDTO;
import org.example.inventory.dtos.respon.CategoryResponse;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.models.Category;
import org.example.inventory.repository.CategoryReponsitory;
import org.example.inventory.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryReponsitory categoryReponsitory;
    public  CategoryResponse toCategoryResponse(Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryResponse;
    }
    @Override
    public CategoryResponse createCategory(CategoryDTO categoryDTO) {
        log.info("createCategory.............");
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        Category c = categoryReponsitory.save(category);
        log.info("createCategory successfully : " + c.getName());
        return toCategoryResponse(c);
    }
    @Override
    public List<CategoryResponse> getAllCategories() {
        log.info("getAllCategories....................................");
        List<CategoryResponse> categories = categoryReponsitory.findAll().stream()
                .map(this::toCategoryResponse)
                .toList();
        log.info("getAllCategories successfully. Total categories: " + categories.size());
        return categories;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        log.info("getCategoryById...................................");
        Category c = categoryReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        log.info("getCategoryById successfully : " + c.getName());
        return toCategoryResponse(c);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryDTO categoryDTO) {
        log.info("updateCategory...................................");
        Category category= categoryReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        if(categoryDTO.getName() != null){
            category.setName(categoryDTO.getName());
        }
        Category updatedCategory= categoryReponsitory.save(category);
        log.info("updateCategory successfully : " + updatedCategory.getName());
        return toCategoryResponse(updatedCategory);
    }

    @Override
    @Transactional()
    public void deleteCategory(Long id) {
        log.info("deleteCategory with id: " + id);
        Category category= categoryReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryReponsitory.delete(category);
        log.info("deleteCategory successfully : " + category.getName());

    }


}
