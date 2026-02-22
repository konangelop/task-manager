package com.taskmanager.repository;

import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Task entity.
 * 
 * Spring Data JPA automatically implements these methods based on naming conventions.
 * This is similar to how you might create custom hooks in React that encapsulate
 * data fetching logic.
 * 
 * These query methods will be called from your React frontend via the API:
 * - GET /api/tasks → findAll()
 * - GET /api/tasks?completed=true → findByCompleted(true)
 * - GET /api/tasks?priority=HIGH → findByPriority(Priority.HIGH)
 * - GET /api/tasks?categoryId=1 → findByCategoryId(1L)
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find tasks by completion status
    List<Task> findByCompleted(boolean completed);

    // Find tasks by priority
    List<Task> findByPriority(Priority priority);

    // Find tasks by category
    List<Task> findByCategoryId(Long categoryId);

    // Find tasks due on a specific date
    List<Task> findByDueDate(LocalDate dueDate);

    // Find tasks due before a date (overdue tasks)
    List<Task> findByDueDateBeforeAndCompletedFalse(LocalDate date);

    // Find tasks due today or earlier that are not completed
    @Query("SELECT t FROM Task t WHERE t.dueDate <= :date AND t.completed = false ORDER BY t.dueDate ASC")
    List<Task> findOverdueTasks(@Param("date") LocalDate date);

    // Search tasks by title (case-insensitive)
    List<Task> findByTitleContainingIgnoreCase(String title);

    // Find tasks ordered by due date
    List<Task> findAllByOrderByDueDateAsc();

    // Find tasks ordered by priority (urgent first)
    List<Task> findAllByOrderByPriorityDesc();

    // Count tasks by completion status
    long countByCompleted(boolean completed);

    // Count tasks by category
    long countByCategoryId(Long categoryId);
}
