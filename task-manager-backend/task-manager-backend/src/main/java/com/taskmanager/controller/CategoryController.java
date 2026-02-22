package com.taskmanager.controller;

import com.taskmanager.dto.CategoryRequest;
import com.taskmanager.model.Category;
import com.taskmanager.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for Category operations.
 * 
 * Endpoints:
 * GET    /api/categories              - Get all categories
 * GET    /api/categories/{id}         - Get a specific category
 * POST   /api/categories              - Create a new category
 * PUT    /api/categories/{id}         - Update a category
 * DELETE /api/categories/{id}         - Delete a category
 * GET    /api/categories/with-counts  - Get categories with task counts
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * GET /api/categories
     * Get all categories
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * GET /api/categories/{id}
     * Get a specific category by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    /**
     * POST /api/categories
     * Create a new category
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    /**
     * PUT /api/categories/{id}
     * Update an existing category
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    /**
     * DELETE /api/categories/{id}
     * Delete a category
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/categories/with-counts
     * Get all categories with their task counts
     * 
     * Returns:
     * [
     *   { "category": {...}, "taskCount": 5 },
     *   { "category": {...}, "taskCount": 12 }
     * ]
     */
    @GetMapping("/with-counts")
    public ResponseEntity<List<Map<String, Object>>> getCategoriesWithTaskCounts() {
        return ResponseEntity.ok(categoryService.getCategoriesWithTaskCount());
    }
}
