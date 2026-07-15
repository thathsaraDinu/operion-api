package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.TaskCreateRequest;
import com.dinoryn.worksphere.dto.TaskResponse;
import com.dinoryn.worksphere.dto.TaskUpdateRequest;
import com.dinoryn.worksphere.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {

    TaskResponse createTask(TaskCreateRequest request);

    TaskResponse getTaskById(Long id);

    Page<TaskResponse> getAllTasks(Pageable pageable);

    TaskResponse updateTask(Long id, TaskUpdateRequest request);

    void deleteTask(Long id);

    Page<TaskResponse> getTasksByProject(
            Long projectId,
            Pageable pageable
    );

    Page<TaskResponse> getTasksByEmployee(
            Long employeeId,
            Pageable pageable
    );

    Page<TaskResponse> getTasksByStatus(
            TaskStatus status,
            Pageable pageable
    );
}