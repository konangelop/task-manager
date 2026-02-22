package com.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Category entity for organizing tasks.
 * 
 * In your React/TypeScript frontend, this will map to:
 * 
 * interface Category {
 *   id: number;
 *   name: string;
 *   color: string;
 *   description?: string;  // optional
 *   createdAt: string;     // ISO date string
 * }
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 1, max = 50, message = "Category name must be between 1 and 50 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 7, message = "Color must be a valid hex code (e.g., #FF5733)")
    @Column(length = 7)
    private String color = "#6B7280"; // Default gray color

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
