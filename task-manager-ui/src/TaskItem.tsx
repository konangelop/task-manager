import type {Task} from "./types.ts";

interface TaskItemProps {
    task: Task
}

export function TaskItem({task}: TaskItemProps) {
    return <li>
        <input type="checkbox" id={String(task.id)} checked={task.completed}/>
        <label htmlFor={String(task.id)}>{task.title}</label>
    </li>;
}