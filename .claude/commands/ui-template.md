---
allowed-tools: Write, Edit, Read
description: Scaffold the HTML (JSX) and CSS skeleton for the current React learning task — structure only, no logic or state
---

## Context

Current task progress:
!`grep -A 30 "Frontend Learning Tasks" CLAUDE.md`

Files already present in task-manager-ui/src/:
!`find task-manager-ui/src -type f \( -name "*.tsx" -o -name "*.css" \) | sort 2>/dev/null`

## Your task

You are scaffolding visual structure for a React learning project.

**Step 1 — Identify the active task**
Find the first row with ⬜ in the task table above.

**Step 2 — Consult the decision table**

| Active task | HTML/CSS needed? | Files to create/update |
|-------------|-----------------|------------------------|
| Task 1 — types.ts | No | — |
| Task 2 — App component | Yes | Update `src/App.tsx` (replace Vite boilerplate with heading shell); update `src/App.css` (strip boilerplate, keep basic layout styles) |
| Task 3 — TaskList | Yes | Create `src/TaskList.tsx` (ul skeleton); create `src/TaskList.css` (basic list styles) |
| Task 4 — TaskItem | Yes | Create `src/TaskItem.tsx` (li skeleton with title and status placeholders); create `src/TaskItem.css` (item layout) |
| Task 5 — Toggle | Minor | Read `src/TaskItem.tsx` — if no `<input type="checkbox">` is present, add one to the JSX |
| Task 6 — TaskForm | Yes | Create `src/TaskForm.tsx` (form with text input and submit button); create `src/TaskForm.css` (form layout) |
| Task 7 — Filter | Yes | Create `src/FilterBar.tsx` (two `<select>` elements for status and priority); create `src/FilterBar.css` |
| Task 8 — Priority badge | Minor | Read `src/TaskItem.tsx` — if no `.priority-badge` span is present, add one to the JSX |

**Step 3 — Check if already implemented**
For the active task, use the Read tool to inspect the target file(s). Look for recognizable
task-manager markup (not Vite boilerplate). Specifically:
- Task 2: App.tsx is "already implemented" if it does NOT contain "vite" and DOES contain a heading
- Tasks 3–8: file is "already implemented" if the target component file exists and has a `return (` with relevant JSX elements

If already implemented, respond with exactly this sentence and stop:

> No HTML or CSS is required or it is already implemented.

**Step 4 — Scaffold**
Write ONLY JSX structure and CSS. Hard rules:
- No `useState`, `useEffect`, `fetch`, or any hook
- No TypeScript type annotations or interface imports
- No `.map()`, `.filter()`, event handlers, or callback props
- Use static placeholder text for dynamic values (e.g., `"Task title"`, `false` for booleans)
- New `.tsx` files use `export default function ComponentName()` with a single `return (...)` block
- No `import React` needed (Vite handles the JSX transform)
- CSS class names should match the JSX `className` attributes

**Step 5 — Summarize**
After writing files, give a short (2–4 sentence) summary: what files were created or updated,
what HTML elements are in each, and what the student should fill in next.
