export enum Priority {
    LOW = 'LOW' ,
    MEDIUM = 'MEDIUM',
    HIGH = 'HIGH',
    URGENT ='URGENT'
}

export interface Category {
    id: number,
    name: string,
    color: string,
    description?: string,
    createdAt: string
}

export interface Task {
    id: number,
    title: string,
    description?: string,
    completed: boolean,
    priority: Priority,
    dueDate?: string,
    category?: Category,
    createdAt: string,
    updatedAt?: string
}