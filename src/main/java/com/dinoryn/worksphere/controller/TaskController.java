package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.TaskCreateRequest;
import com.dinoryn.worksphere.dto.TaskResponse;
import com.dinoryn.worksphere.dto.TaskUpdateRequest;
import com.dinoryn.worksphere.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskCreateRequest request
    ) {

        return new ResponseEntity<>(
                taskService.createTask(request),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                taskService.getTaskById(id)
        );
    }


    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {

        return ResponseEntity.ok(
                taskService.getAllTasks()
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request
    ) {

        return ResponseEntity.ok(
                taskService.updateTask(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id
    ) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}