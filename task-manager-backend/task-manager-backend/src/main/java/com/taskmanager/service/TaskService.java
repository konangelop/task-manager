package com.taskmanager.service;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.Category;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.repository.CategoryRepository;
import com.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Service layer for Task business logic.
 * 
 * The service layer contains business logic and acts as a bridge between
 * the controller (API) and repository (database) layers.
 * 
 * In React/TypeScript, you'll create service modules that mirror this:
 * 
 * // services/taskService.ts
 * export const taskService = {
 *   getAllTasks: () => api.get<Task[]>('/tasks'),
 *   getTask: (id: number) => api.get<Task>(`/tasks/${id}`),
 *   createTask: (data: CreateTaskRequest) => api.post<Task>('/tasks', data),
 *   updateTask: (id: number, data: UpdateTaskRequest) => api.put<Task>(`/tasks/${id}`, data),
 *   deleteTask: (id: number) => api.delete(`/tasks/${id}`),
 *   toggleComplete: (id: number) => api.patch<Task>(`/tasks/${id}/toggle`),
 * };
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Get all tasks
     */
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Get a task by ID
     */
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));
    }

    /**
     * Create a new task
     */
    public Task createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority() != null ? request.getPriority() : Priority.MEDIUM);
        task.setDueDate(request.getDueDate());
        task.setCompleted(false);

        // Set category if provided
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
            task.setCategory(category);
        }

        return taskRepository.save(task);
    }

    /**
     * Update an existing task
     */
    public Task updateTask(Long id, TaskRequest request) {
        Task task = getTaskById(id);

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }
        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }
        if (request.getCompleted() != null) {
            task.setCompleted(request.getCompleted());
        }
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
            task.setCategory(category);
        }

        return taskRepository.save(task);
    }

    /**
     * Delete a task
     */
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task", id);
        }
        taskRepository.deleteById(id);
    }

    /**
     * Toggle task completion status
     * 
     * This is a common pattern you'll implement in React:
     * 
     * const toggleTask = async (id: number) => {
     *   const updatedTask = await taskService.toggleComplete(id);
     *   setTasks(tasks.map(t => t.id === id ? updatedTask : t));
     * };
     */
    public Task toggleComplete(Long id) {
        Task task = getTaskById(id);
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }

    /**
     * Get tasks by completion status
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    /**
     * Get tasks by priority
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    /**
     * Get tasks by category
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksByCategory(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    /**
     * Get overdue tasks (due date passed and not completed)
     */
    @Transactional(readOnly = true)
    public List<Task> getOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDate.now());
    }

    /**
     * Search tasks by title
     */
    @Transactional(readOnly = true)
    public List<Task> searchTasks(String query) {
        return taskRepository.findByTitleContainingIgnoreCase(query);
    }

    /**
     * Get task statistics
     * 
     * Returns counts that can be displayed in a dashboard:
     * {
     *   "total": 25,
     *   "completed": 15,
     *   "pending": 10,
     *   "overdue": 3
     * }
     */
    @Transactional(readOnly = true)
    public Map<String, Long> getTaskStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", taskRepository.count());
        stats.put("completed", taskRepository.countByCompleted(true));
        stats.put("pending", taskRepository.countByCompleted(false));
        stats.put("overdue", (long) taskRepository.findOverdueTasks(LocalDate.now()).size());
        return stats;
    }
}
