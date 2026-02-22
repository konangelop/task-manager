package com.taskmanager.service;

import com.taskmanager.dto.CategoryRequest;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.Category;
import com.taskmanager.repository.CategoryRepository;
import com.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer for Category business logic.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    /**
     * Get all categories
     */
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get a category by ID
     */
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
    }

    /**
     * Create a new category
     */
    public Category createCategory(CategoryRequest request) {
        // Check if category name already exists
        if (categoryRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Category with name '" + request.getName() + "' already exists");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setColor(request.getColor() != null ? request.getColor() : "#6B7280");
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    /**
     * Update an existing category
     */
    public Category updateCategory(Long id, CategoryRequest request) {
        Category category = getCategoryById(id);

        if (request.getName() != null && !request.getName().equals(category.getName())) {
            // Check if new name conflicts with existing category
            if (categoryRepository.existsByName(request.getName())) {
                throw new IllegalArgumentException("Category with name '" + request.getName() + "' already exists");
            }
            category.setName(request.getName());
        }
        if (request.getColor() != null) {
            category.setColor(request.getColor());
        }
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }

        return categoryRepository.save(category);
    }

    /**
     * Delete a category
     */
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category", id);
        }
        categoryRepository.deleteById(id);
    }

    /**
     * Get category with task count
     * 
     * Useful for displaying categories with their task counts:
     * [
     *   { "category": {...}, "taskCount": 5 },
     *   { "category": {...}, "taskCount": 12 }
     * ]
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getCategoriesWithTaskCount() {
        List<Category> categories = categoryRepository.findAll();
        
        return categories.stream().map(category -> {
            Map<String, Object> result = new HashMap<>();
            result.put("category", category);
            result.put("taskCount", taskRepository.countByCategoryId(category.getId()));
            return result;
        }).toList();
    }
}
