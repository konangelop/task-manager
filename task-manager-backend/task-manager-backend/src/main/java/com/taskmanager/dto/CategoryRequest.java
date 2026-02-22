package com.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Category creation and updates.
 * 
 * In TypeScript:
 * 
 * interface CreateCategoryRequest {
 *   name: string;
 *   color?: string;       // hex color like "#FF5733"
 *   description?: string;
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(min = 1, max = 50, message = "Category name must be between 1 and 50 characters")
    private String name;

    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Color must be a valid hex code (e.g., #FF5733)")
    private String color = "#6B7280";

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
}
