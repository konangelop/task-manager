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
