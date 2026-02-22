package com.taskmanager.controller;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for Task operations.
 * 
 * This controller exposes the following endpoints that you'll consume in React:
 * 
 * GET    /api/tasks              - Get all tasks (with optional filters)
 * GET    /api/tasks/{id}         - Get a specific task
 * POST   /api/tasks              - Create a new task
 * PUT    /api/tasks/{id}         - Update a task
 * DELETE /api/tasks/{id}         - Delete a task
 * PATCH  /api/tasks/{id}/toggle  - Toggle task completion
 * GET    /api/tasks/stats        - Get task statistics
 * GET    /api/tasks/search       - Search tasks by title
 * GET    /api/tasks/overdue      - Get overdue tasks
 * 
 * In React/TypeScript, you'll call these using fetch or axios:
 * 
 * // Using fetch with TypeScript
 * const getTasks = async (): Promise<Task[]> => {
 *   const response = await fetch('/api/tasks');
 *   if (!response.ok) throw new Error('Failed to fetch tasks');
 *   return response.json();
 * };
 * 
 * // Using axios (more concise)
 * const getTasks = () => axios.get<Task[]>('/api/tasks').then(res => res.data);
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class TaskController {

    private final TaskService taskService;

    /**
     * GET /api/tasks
     * Get all tasks with optional filters
     * 
     * Query params:
     * - completed: boolean - filter by completion status
     * - priority: string - filter by priority (LOW, MEDIUM, HIGH, URGENT)
     * - categoryId: number - filter by category
     * 
     * Example React usage:
     * 
     * const [filter, setFilter] = useState<'all' | 'completed' | 'pending'>('all');
     * 
     * useEffect(() => {
     *   const params = new URLSearchParams();
     *   if (filter !== 'all') {
     *     params.append('completed', filter === 'completed' ? 'true' : 'false');
     *   }
     *   fetch(`/api/tasks?${params}`)
     *     .then(res => res.json())
     *     .then(setTasks);
     * }, [filter]);
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Long categoryId) {
        
        List<Task> tasks;
        
        if (completed != null) {
            tasks = taskService.getTasksByCompleted(completed);
        } else if (priority != null) {
            tasks = taskService.getTasksByPriority(priority);
        } else if (categoryId != null) {
            tasks = taskService.getTasksByCategory(categoryId);
        } else {
            tasks = taskService.getAllTasks();
        }
        
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/{id}
     * Get a specific task by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    /**
     * POST /api/tasks
     * Create a new task
     * 
     * Example React usage:
     * 
     * const createTask = async (data: CreateTaskRequest) => {
     *   const response = await fetch('/api/tasks', {
     *     method: 'POST',
     *     headers: { 'Content-Type': 'application/json' },
     *     body: JSON.stringify(data),
     *   });
     *   if (!response.ok) {
     *     const error = await response.json();
     *     throw new Error(error.message);
     *   }
     *   return response.json();
     * };
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task task = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    /**
     * PUT /api/tasks/{id}
     * Update an existing task
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    /**
     * DELETE /api/tasks/{id}
     * Delete a task
     * 
     * Returns 204 No Content on success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * PATCH /api/tasks/{id}/toggle
     * Toggle task completion status
     * 
     * Example React usage:
     * 
     * const toggleTask = async (id: number) => {
     *   const updatedTask = await fetch(`/api/tasks/${id}/toggle`, {
     *     method: 'PATCH',
     *   }).then(res => res.json());
     *   
     *   setTasks(tasks.map(t => t.id === id ? updatedTask : t));
     * };
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleComplete(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.toggleComplete(id));
    }

    /**
     * GET /api/tasks/stats
     * Get task statistics
     * 
     * Returns: { total, completed, pending, overdue }
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getTaskStats() {
        return ResponseEntity.ok(taskService.getTaskStats());
    }

    /**
     * GET /api/tasks/search?q={query}
     * Search tasks by title
     */
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String q) {
        return ResponseEntity.ok(taskService.searchTasks(q));
    }

    /**
     * GET /api/tasks/overdue
     * Get all overdue tasks
     */
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        return ResponseEntity.ok(taskService.getOverdueTasks());
    }
}
