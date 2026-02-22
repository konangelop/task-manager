package com.taskmanager.repository;

import com.taskmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find category by name
    Optional<Category> findByName(String name);

    // Check if category name exists
    boolean existsByName(String name);

    // Find category by name (case-insensitive)
    Optional<Category> findByNameIgnoreCase(String name);
}
