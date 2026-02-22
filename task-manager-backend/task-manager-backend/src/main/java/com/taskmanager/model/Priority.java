package com.taskmanager.model;

/**
 * Priority levels for tasks.
 * 
 * This enum will help you learn about:
 * - TypeScript enums and how they differ from Java enums
 * - Union types as an alternative to enums in TypeScript
 * - Type-safe select inputs in React forms
 */
public enum Priority {
    LOW("Low Priority"),
    MEDIUM("Medium Priority"),
    HIGH("High Priority"),
    URGENT("Urgent");

    private final String displayName;

    Priority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
