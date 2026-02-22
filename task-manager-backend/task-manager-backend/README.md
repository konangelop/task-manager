# Task Manager Backend

A Spring Boot REST API for the Task Manager application - your learning project for React and TypeScript.

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Run the Application

```bash
cd task-manager-backend
./mvnw spring-boot:run
```

Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

The server will start at `http://localhost:8080`

### Access Points
- **API Base URL:** http://localhost:8080/api
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:taskmanagerdb`
  - Username: `sa`
  - Password: (leave empty)

---

## API Reference

### Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |
| PATCH | `/api/tasks/{id}/toggle` | Toggle completion |
| GET | `/api/tasks/stats` | Get task statistics |
| GET | `/api/tasks/search?q={query}` | Search tasks |
| GET | `/api/tasks/overdue` | Get overdue tasks |

#### Query Parameters for GET /api/tasks
- `completed` (boolean) - Filter by completion status
- `priority` (string) - Filter by priority: LOW, MEDIUM, HIGH, URGENT
- `categoryId` (number) - Filter by category ID

#### Task Request Body (POST/PUT)
```json
{
  "title": "Complete project",
  "description": "Optional description",
  "priority": "HIGH",
  "dueDate": "2024-01-20",
  "categoryId": 1,
  "completed": false
}
```

#### Task Response
```json
{
  "id": 1,
  "title": "Complete project",
  "description": "Optional description",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2024-01-20",
  "category": {
    "id": 1,
    "name": "Work",
    "color": "#3B82F6",
    "description": "Work-related tasks",
    "createdAt": "2024-01-15T10:30:00"
  },
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | Get all categories |
| GET | `/api/categories/{id}` | Get category by ID |
| POST | `/api/categories` | Create a new category |
| PUT | `/api/categories/{id}` | Update a category |
| DELETE | `/api/categories/{id}` | Delete a category |
| GET | `/api/categories/with-counts` | Get categories with task counts |

#### Category Request Body (POST/PUT)
```json
{
  "name": "Work",
  "color": "#3B82F6",
  "description": "Work-related tasks"
}
```

---

## TypeScript Interfaces

Use these interfaces in your React frontend:

```typescript
// types/task.ts

export type Priority = 'LOW' | 'MEDIUM' | 'HIGH' | 'URGENT';

export interface Category {
  id: number;
  name: string;
  color: string;
  description?: string;
  createdAt: string;
}

export interface Task {
  id: number;
  title: string;
  description?: string;
  completed: boolean;
  priority: Priority;
  dueDate?: string;
  category?: Category;
  createdAt: string;
  updatedAt: string;
}

// Request types
export interface CreateTaskRequest {
  title: string;
  description?: string;
  priority?: Priority;
  dueDate?: string;
  categoryId?: number;
}

export interface UpdateTaskRequest {
  title?: string;
  description?: string;
  priority?: Priority;
  dueDate?: string;
  categoryId?: number;
  completed?: boolean;
}

export interface CreateCategoryRequest {
  name: string;
  color?: string;
  description?: string;
}

export interface TaskStats {
  total: number;
  completed: number;
  pending: number;
  overdue: number;
}

export interface ApiError {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  fieldErrors?: Record<string, string>;
}
```

---

## Example API Calls (for React)

### Using Fetch

```typescript
const API_BASE = 'http://localhost:8080/api';

// Get all tasks
async function getTasks(): Promise<Task[]> {
  const response = await fetch(`${API_BASE}/tasks`);
  if (!response.ok) throw new Error('Failed to fetch tasks');
  return response.json();
}

// Create a task
async function createTask(data: CreateTaskRequest): Promise<Task> {
  const response = await fetch(`${API_BASE}/tasks`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message);
  }
  return response.json();
}

// Toggle task completion
async function toggleTask(id: number): Promise<Task> {
  const response = await fetch(`${API_BASE}/tasks/${id}/toggle`, {
    method: 'PATCH',
  });
  return response.json();
}

// Delete a task
async function deleteTask(id: number): Promise<void> {
  const response = await fetch(`${API_BASE}/tasks/${id}`, {
    method: 'DELETE',
  });
  if (!response.ok) throw new Error('Failed to delete task');
}
```

### Using Axios (recommended)

```typescript
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: { 'Content-Type': 'application/json' },
});

export const taskService = {
  getAll: () => api.get<Task[]>('/tasks').then(res => res.data),
  getById: (id: number) => api.get<Task>(`/tasks/${id}`).then(res => res.data),
  create: (data: CreateTaskRequest) => api.post<Task>('/tasks', data).then(res => res.data),
  update: (id: number, data: UpdateTaskRequest) => api.put<Task>(`/tasks/${id}`, data).then(res => res.data),
  delete: (id: number) => api.delete(`/tasks/${id}`),
  toggle: (id: number) => api.patch<Task>(`/tasks/${id}/toggle`).then(res => res.data),
  getStats: () => api.get<TaskStats>('/tasks/stats').then(res => res.data),
  search: (query: string) => api.get<Task[]>(`/tasks/search?q=${query}`).then(res => res.data),
};
```

---

## Sample Data

The application initializes with sample data including:

**Categories:**
- Work (Blue)
- Personal (Green)
- Shopping (Orange)
- Health (Red)
- Learning (Purple)

**Tasks:** 15 sample tasks across all categories with various priorities and due dates.

---

## CORS Configuration

The backend allows requests from:
- http://localhost:5173 (Vite)
- http://localhost:3000 (Create React App)

---

## React Learning Topics This Backend Supports

1. **Data Fetching** - useEffect, useState for loading tasks
2. **Form Handling** - Creating and editing tasks with validation
3. **TypeScript Interfaces** - Modeling the API response types
4. **State Management** - Managing task list state
5. **Conditional Rendering** - Completed vs pending tasks
6. **Filtering & Search** - Using query parameters
7. **Error Handling** - Handling API errors gracefully
8. **Optimistic Updates** - Updating UI before server confirms
9. **Loading States** - Showing loading indicators

Good luck with your React/TypeScript learning journey! 🚀
