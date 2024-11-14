package com.example.todov1.controller;
import com.example.todov1.model.Todo;
import com.example.todov1.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // Allow requests from React app running on localhost:3000

@RestController

public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    @GetMapping
//    public List<Todo> getAllTodos() {
//        return todoService.getAllTodos();
//    }


    @GetMapping
    public Page<Todo> getAllTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean completed) {

        Pageable pageable = PageRequest.of(page, size);

        // If completed parameter is provided, use it to filter, else return all todos
        System.out.println("Completed filter: " + completed);

        if (completed != null) {
            return todoService.getTodosByCompleted(completed, pageable);
        } else {
            return todoService.getAllTodos(pageable);
        }
    }
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    // New method to update a todo
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        return todoService.updateTodo(id, updatedTodo);
    }
//    @GetMapping("/user/{username}")
//    public Page<Todo> getTodosByUsername(
//            @PathVariable String username,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        return todoService.getTodosByUsername(username, pageable);
//    }
}
