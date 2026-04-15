package com.blogapi.controller;

import com.blogapi.dto.CategoryDto;
import com.blogapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Tag(name = "Category REST APIs", description = "Create, Read, Update, Delete Categories")
public class CategoryController {

    private CategoryService categoryService;

    // POST /api/categories  (Admin only)
    @Operation(summary = "Create a new category", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    // GET /api/categories/{id}
    @Operation(summary = "Get a category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    // GET /api/categories
    @Operation(summary = "Get all categories")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // PUT /api/categories/{id}  (Admin only)
    @Operation(summary = "Update a category", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CategoryDto categoryDto,
            @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, id));
    }

    // DELETE /api/categories/{id}  (Admin only)
    @Operation(summary = "Delete a category", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
