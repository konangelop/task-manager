# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.2.0 REST API backend for a task management application, designed to integrate with a React/TypeScript frontend. 
It uses H2 in-memory database, so data resets on each restart.
The goal of the project is to build a React + TypeScript frontend on top of an existing Spring Boot backend (running on port 8080).                                                                                                                                                                                                  │
It is not just to build the app, but to learn React and TypeScript concepts progressively. Each task is paired with the concept(s) it teaches.                                                                                                                                                                                                                                                       │


## Commands

```bash
# Run the application (port 8080)
./mvnw spring-boot:run

# Build
./mvnw clean package

# Run tests
./mvnw test
```

Work from the inner directory: `task-manager-backend/task-manager-backend/`

## Architecture

**Layered N-Tier:**
```
Controller (REST) → Service (Business Logic) → Repository (JPA) → H2 (in-memory)
```

**Key packages** under `src/main/java/com/taskmanager/`:
- `controller/` — TaskController (`/api/tasks`) and CategoryController (`/api/categories`)
- `service/` — Business logic, `@Transactional` methods
- `repository/` — Spring Data JPA repos with custom query methods
- `model/` — JPA entities: `Task`, `Category`, `Priority` (enum: LOW/MEDIUM/HIGH/URGENT)
- `dto/` — `TaskRequest` and `CategoryRequest` with Bean Validation annotations
- `exception/` — `GlobalExceptionHandler` (`@RestControllerAdvice`) and `ResourceNotFoundException`
- `config/` — `WebConfig` (CORS: localhost:5173 and localhost:3000), `DataInitializer` (seeds 5 categories + 15 tasks on startup)

## Data Model

**Task** fields: id, title, description, completed, priority, dueDate, category (ManyToOne), createdAt, updatedAt

**Category** fields: id, name (unique), color (hex), description, createdAt

## API Endpoints

- `GET/POST /api/tasks` — list (supports `?completed=`, `?priority=`, `?categoryId=`) or create
- `GET/PUT/DELETE /api/tasks/{id}` — get, update, or delete
- `PATCH /api/tasks/{id}/toggle` — toggle completion
- `GET /api/tasks/stats` — task statistics
- `GET /api/tasks/search?q=` — search by title
- `GET /api/tasks/overdue` — overdue tasks
- `GET/POST /api/categories` — list or create
- `GET/PUT/DELETE /api/categories/{id}` — get, update, or delete
- `GET /api/categories/with-counts` — categories with task counts

## Key Configuration

`application.properties`:
- `spring.jpa.hibernate.ddl-auto=create-drop` — schema recreated on every startup (data is not persisted)
- `spring.h2.console.enabled=true` — H2 web console at `/h2-console`
- `spring.jpa.show-sql=true` — SQL queries logged to console

## Lombok Usage

All classes use Lombok: `@Data`, `@RequiredArgsConstructor` for constructor injection, `@NoArgsConstructor`/`@AllArgsConstructor` on entities.

---

## Frontend Learning Tasks

### Progression Rules
- Explain the concept **before** the student writes code for that task
- Give **hints**, not solutions — only write code if explicitly asked
- Each task must produce **visible output in the browser** before moving on to the next
- After explaining a concept, add it to `.claude/explained-concepts.md` — do not re-explain it in future sessions
- Check the `.claude/explained-concepts.md` file at the start of each session to know where to resume

| # | Status | Task | Concept(s) |
|---|--------|------|------------|
| 1 | ⬜ | Define `src/types.ts` — Task, Category interfaces; Priority enum | TypeScript interfaces, enums, optional fields `?`, type aliases |
| 2 | ⬜ | Bare-bones `App` component — hardcoded heading, no data | React functional components, JSX syntax |
| 3 | ⬜ | `TaskList` — fetch `/api/tasks`, render list | `useEffect` (data fetching), `useState` (storing data), async/await |
| 4 | ⬜ | `TaskItem` — display a single task inside TaskList | Component composition, props with TypeScript types, conditional rendering |
| 5 | ⬜ | Toggle completion — checkbox on TaskItem | Event handling (`React.ChangeEvent`), immutable state updates |
| 6 | ⬜ | `TaskForm` — create a new task | Controlled components, `useState` for form inputs, `onSubmit` / `preventDefault` |
| 7 | ⬜ | Filter by status and priority | Array `filter`/`map` with type safety, lifting state up |
| 8 | ⬜ | Priority badge and category label on TaskItem | Conditional rendering for enum values, component composition |

Update ⬜ → ✅ as each task is completed.
