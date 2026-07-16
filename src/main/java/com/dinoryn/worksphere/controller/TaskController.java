package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.TaskCreateRequest;
import com.dinoryn.worksphere.dto.TaskResponse;
import com.dinoryn.worksphere.dto.TaskUpdateRequest;
import com.dinoryn.worksphere.entity.TaskStatus;
import com.dinoryn.worksphere.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskCreateRequest request
    ) {

        return new ResponseEntity<>(
                taskService.createTask(request),
                HttpStatus.CREATED
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                taskService.getTaskById(id)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                taskService.getAllTasks(pageable)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request
    ) {

        return ResponseEntity.ok(
                taskService.updateTask(id, request)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id
    ) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<TaskResponse>> getTasksByProject(
            @PathVariable Long projectId,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                taskService.getTasksByProject(
                        projectId,
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Page<TaskResponse>> getTasksByEmployee(
            @PathVariable Long employeeId,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                taskService.getTasksByEmployee(
                        employeeId,
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<TaskResponse>> getTasksByStatus(
            @PathVariable TaskStatus status,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                taskService.getTasksByStatus(
                        status,
                        pageable
                )
        );
    }
}