package com.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Task Manager Backend Application
 * 
 * This is the entry point for the Spring Boot application.
 * It serves as the backend API for a React/TypeScript frontend.
 * 
 * Features:
 * - RESTful API for Task management (CRUD operations)
 * - Category management for organizing tasks
 * - Priority levels (LOW, MEDIUM, HIGH, URGENT)
 * - H2 in-memory database for easy development
 * - CORS enabled for React frontend (localhost:5173)
 */
@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("  Task Manager Backend is running!");
        System.out.println("  API: http://localhost:8080/api");
        System.out.println("  H2 Console: http://localhost:8080/h2-console");
        System.out.println("========================================\n");
    }
}
