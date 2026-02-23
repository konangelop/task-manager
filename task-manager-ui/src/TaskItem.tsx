import type {Task} from "./types.ts";

interface TaskItemProps {
    task: Task
    onToggle: (id: number) => void
}

export function TaskItem({task, onToggle}: TaskItemProps) {
    return <li>
        <input type="checkbox" id={String(task.id)} checked={task.completed} onChange={() => onToggle(task.id)}/>
        <label htmlFor={String(task.id)}>{task.title}</label>
    </li>;
}