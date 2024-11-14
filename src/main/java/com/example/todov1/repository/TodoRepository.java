package com.example.todov1.repository;  // Package name

import com.example.todov1.model.Todo;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Indicates this is a repository
public interface TodoRepository extends JpaRepository<Todo, Long> {  // Extends JpaRepository for CRUD operations

    // You can define custom query methods here if needed, for example:
    // List<Todo> findByCompleted(boolean completed);
    @NonNull
    Page<Todo> findAll(@NonNull Pageable pageable);  // Fetch all todos with pagination
    Page<Todo> findByCompleted(boolean completed, Pageable pageable);

}
