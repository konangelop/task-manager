# Task Manager

A full-stack task management application built as a **React + TypeScript learning project**, backed by a Spring Boot REST API.

## Project Objectives

### Primary Goal
Learn React and TypeScript progressively by building a real frontend on top of a working backend — not just to ship an app, but to deeply understand the concepts behind it.

### What You'll Build
A task management UI that lets users:
- View, create, update, and delete tasks
- Toggle task completion
- Filter tasks by status and priority
- Assign tasks to categories
- See priority badges and due dates

### Learning Path

Each frontend task is paired with a specific concept:

| # | Task | Concepts |
|---|------|----------|
| 1 | Define TypeScript types | Interfaces, enums, optional fields, type aliases |
| 2 | Bare-bones `App` component | React functional components, JSX |
| 3 | Fetch and render task list | `useEffect`, `useState`, async/await |
| 4 | `TaskItem` component | Component composition, props, conditional rendering |
| 5 | Toggle task completion | Event handling, immutable state updates |
| 6 | `TaskForm` — create a task | Controlled components, form state, `onSubmit` |
| 7 | Filter by status/priority | Array `filter`/`map`, lifting state up |
| 8 | Priority badge & category label | Conditional rendering for enums |

---

## Tech Stack

### Backend
- **Java 17** + **Spring Boot 3.2.0**
- **Spring Data JPA** with **H2** in-memory database
- **Lombok** for boilerplate reduction
- REST API on `http://localhost:8080`

### Frontend
- **React 18** + **TypeScript**
- **Vite** dev server on `http://localhost:5173`

---

## Getting Started

### 1. Start the backend

```bash
cd task-manager-backend/task-manager-backend
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080/api`.
Sample data (5 categories, 15 tasks) is seeded automatically on startup.

### 2. Start the frontend

```bash
cd task-manager-ui
npm install
npm run dev
```

Open `http://localhost:5173` in your browser.

---

## API Overview

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | List all tasks (filterable) |
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks/{id}` | Get a task |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |
| PATCH | `/api/tasks/{id}/toggle` | Toggle completion |
| GET | `/api/tasks/stats` | Task statistics |
| GET | `/api/tasks/search?q=` | Search by title |
| GET | `/api/categories` | List categories |
| GET | `/api/categories/with-counts` | Categories with task counts |

---

## Project Structure

```
task-manager/
├── task-manager-backend/   # Spring Boot API
└── task-manager-ui/        # React + TypeScript frontend
```
