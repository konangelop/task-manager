# Concepts Already Explained

Do NOT re-explain these unless the user explicitly asks.

---

## From the plan's pre-explained list
- Mental model shifts — Java imperative vs React declarative, props vs @Autowired, never mutate state
- Project setup — Vite, Node.js, `npm create vite@latest`, `npm run dev`, port 5173
- TypeScript intro — interfaces mirror Java interfaces, enums, lowercase primitives (string/number/boolean), Long→number, LocalDate→string (ISO)

---

## Task 1 — TypeScript interfaces & enums (explained, not yet written by student)
- `enum Priority` with string values (vs Java integer enums)
- `interface` for data shapes — structural typing, not nominal
- `?` for optional fields (like @Nullable / Optional<>)
- Primitive types lowercase: string, number, boolean
- Long → number, LocalDate → string (ISO)
- `export` keyword on each declaration
- Type aliases — a nickname for an existing type (e.g. `type DateString = string`), purely for readability, no runtime cost, treated identically to the original type by TypeScript

---

## Task 2 — React functional components & JSX
- React functional components — a component is just a function that returns UI, no class/annotation needed, name must start with a capital letter, React calls it when rendering
- JSX syntax — HTML-like syntax inside TypeScript, compiled away before the browser sees it, `class`→`className`, all tags must close, single root element rule, `{}` is the TypeScript escape hatch

---

## Task 3 — useEffect, useState, async/await
- `useState` — component memory that persists across re-renders; returns [value, setter]; never mutate directly, always use the setter; changing it triggers a re-render
- `useEffect` — where side effects live (data fetching, timers, subscriptions); runs after render; dependency array `[]` means run once on mount (like @PostConstruct)
- async/await in useEffect — `useEffect`'s function cannot itself be async; define an async function inside the effect and call it immediately
- `fetch` — browser built-in for HTTP requests; returns a Promise; await the response, then await `.json()` to parse the body
