package com.taskmanager.config;

import com.taskmanager.model.Category;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.repository.CategoryRepository;
import com.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Initializes the database with sample data on application startup.
 * 
 * This is helpful for development and testing - you'll have data to work
 * with immediately when building your React frontend.
 * 
 * Since we're using H2 in-memory database with create-drop, this data
 * is recreated each time you restart the application.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        // Create categories
        Category work = createCategory("Work", "#3B82F6", "Work-related tasks");
        Category personal = createCategory("Personal", "#10B981", "Personal tasks and errands");
        Category shopping = createCategory("Shopping", "#F59E0B", "Shopping lists and purchases");
        Category health = createCategory("Health", "#EF4444", "Health and fitness tasks");
        Category learning = createCategory("Learning", "#8B5CF6", "Learning and education");

        // Create sample tasks
        
        // Work tasks
        createTask("Complete project proposal", 
                   "Write and submit the Q1 project proposal document", 
                   Priority.HIGH, 
                   LocalDate.now().plusDays(2), 
                   work, 
                   false);
        
        createTask("Review pull requests", 
                   "Review the pending PRs from the team", 
                   Priority.MEDIUM, 
                   LocalDate.now(), 
                   work, 
                   false);
        
        createTask("Prepare presentation slides", 
                   "Create slides for the Monday team meeting", 
                   Priority.URGENT, 
                   LocalDate.now().plusDays(1), 
                   work, 
                   false);
        
        createTask("Update documentation", 
                   "Update the API documentation with new endpoints", 
                   Priority.LOW, 
                   LocalDate.now().plusDays(7), 
                   work, 
                   true);

        // Personal tasks
        createTask("Call mom", 
                   "Weekly catch-up call", 
                   Priority.MEDIUM, 
                   LocalDate.now(), 
                   personal, 
                   false);
        
        createTask("Pay utility bills", 
                   "Electricity and internet bills due", 
                   Priority.HIGH, 
                   LocalDate.now().minusDays(1), // Overdue!
                   personal, 
                   false);
        
        createTask("Schedule dentist appointment", 
                   null, 
                   Priority.LOW, 
                   LocalDate.now().plusDays(14), 
                   personal, 
                   false);

        // Shopping tasks
        createTask("Buy groceries", 
                   "Milk, eggs, bread, vegetables", 
                   Priority.MEDIUM, 
                   LocalDate.now().plusDays(1), 
                   shopping, 
                   false);
        
        createTask("Order new headphones", 
                   "Research and order noise-canceling headphones", 
                   Priority.LOW, 
                   null, // No due date
                   shopping, 
                   true);

        // Health tasks
        createTask("Morning run", 
                   "30-minute jog in the park", 
                   Priority.MEDIUM, 
                   LocalDate.now(), 
                   health, 
                   true);
        
        createTask("Book gym session", 
                   "Book a session with the trainer", 
                   Priority.LOW, 
                   LocalDate.now().plusDays(3), 
                   health, 
                   false);

        // Learning tasks
        createTask("Complete React tutorial", 
                   "Finish the React fundamentals course", 
                   Priority.HIGH, 
                   LocalDate.now().plusDays(5), 
                   learning, 
                   false);
        
        createTask("Practice TypeScript", 
                   "Work through TypeScript exercises", 
                   Priority.MEDIUM, 
                   LocalDate.now().plusDays(3), 
                   learning, 
                   false);
        
        createTask("Read Clean Code chapter", 
                   "Read chapter 5 of Clean Code", 
                   Priority.LOW, 
                   LocalDate.now().plusDays(7), 
                   learning, 
                   false);

        // Task without category
        createTask("Random idea", 
                   "Think about this later...", 
                   Priority.LOW, 
                   null, 
                   null, 
                   false);

        System.out.println("✓ Sample data initialized: " + 
                           categoryRepository.count() + " categories, " + 
                           taskRepository.count() + " tasks");
    }

    private Category createCategory(String name, String color, String description) {
        Category category = new Category();
        category.setName(name);
        category.setColor(color);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    private Task createTask(String title, String description, Priority priority, 
                           LocalDate dueDate, Category category, boolean completed) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        task.setCategory(category);
        task.setCompleted(completed);
        return taskRepository.save(task);
    }
}
