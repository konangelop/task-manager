import {useState, useEffect} from "react";
import axios from 'axios';
import type {Task} from "./types.ts";

export function TaskList() {
    const [tasks, setTasks] = useState<Task[]>([])
    useEffect(() => {
        async function loadTasks(){
            const response = await axios.get<Task[]>('/api/tasks');
            setTasks(response.data);
        }
        loadTasks();
    }, []);
    return (
        <ul>
            {tasks.map(task => (
                <li key={task.id}>{task.title}</li>
            ))}
        </ul>
    );
}