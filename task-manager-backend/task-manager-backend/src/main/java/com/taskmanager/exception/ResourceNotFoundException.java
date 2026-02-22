package com.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 * 
 * The @ResponseStatus annotation automatically converts this to a 404 response.
 * 
 * In your React frontend, you'll handle this:
 * 
 * try {
 *   const task = await api.getTask(id);
 * } catch (error) {
 *   if (error.response?.status === 404) {
 *     // Handle not found - show error message or redirect
 *   }
 * }
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s with id %d not found", resourceName, id));
    }
}
