package org.example.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inventory.dtos.request.CategoryDTO;
import org.example.inventory.dtos.respon.CategoryResponse;
import org.example.inventory.dtos.respon.DataResponse;
import org.example.inventory.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    // Create a new category
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        CategoryResponse categoryResponse = categoryService.createCategory(categoryDTO);
        return DataResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Category created successfully")
                .data(categoryResponse)
                .build();
    }

    //get all categories
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public DataResponse<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return DataResponse.<List<CategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all categories successfully")
                .data(categories)
                .build();
    }
    //get category by id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public DataResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return DataResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get category by id successfully")
                .data(categoryResponse)
                .build();
    }
    //update category
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryDTO);
        return DataResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Update category successfully")
                .data(categoryResponse)
                .build();
    }
}
