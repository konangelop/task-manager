package com.taskmanager.dto;

import com.taskmanager.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data Transfer Object for Task creation and updates.
 * 
 * DTOs help separate the API contract from the internal entity structure.
 * This is important because:
 * 1. You don't expose internal fields (like createdAt) to clients
 * 2. You can validate input before it reaches the service layer
 * 3. You can evolve the API independently of the database schema
 * 
 * In TypeScript, you'll create a similar type:
 * 
 * interface CreateTaskRequest {
 *   title: string;
 *   description?: string;
 *   priority?: 'LOW' | 'MEDIUM' | 'HIGH' | 'URGENT';
 *   dueDate?: string;
 *   categoryId?: number;
 * }
 * 
 * // For updates, you might use Partial<>
 * type UpdateTaskRequest = Partial<CreateTaskRequest>;
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "Task title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Priority priority = Priority.MEDIUM;

    private LocalDate dueDate;

    private Long categoryId;

    private Boolean completed;
}
