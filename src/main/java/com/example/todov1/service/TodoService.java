package com.example.todov1.service;

import com.example.todov1.model.Todo;
import com.example.todov1.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

//    public List<Todo> getAllTodos() {
//        return todoRepository.findAll();
//    }
    public Page<Todo> getAllTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);  // Fetch all todos with pagination
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }
    public Page<Todo> getTodosByCompleted(boolean completed, Pageable pageable) {
        return todoRepository.findByCompleted(completed, pageable);  // Filter todos by completed status
    }
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
    // Method to update a todo
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodoOpt = todoRepository.findById(id);

        if (existingTodoOpt.isPresent()) {
            Todo existingTodo = existingTodoOpt.get();
            // Update the fields (you can add more fields if necessary)
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setCompleted(updatedTodo.isCompleted());

            return todoRepository.save(existingTodo); // Save the updated todo to the database
        } else {
            throw new RuntimeException("Todo not found with id " + id);
        }
    }

//    public Page<Todo> getTodosByUsername(String username, Pageable pageable) {
//        return todoRepository.findByUsername(username, pageable);
//    }
}
