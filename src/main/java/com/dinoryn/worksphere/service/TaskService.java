package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.TaskCreateRequest;
import com.dinoryn.worksphere.dto.TaskResponse;
import com.dinoryn.worksphere.dto.TaskUpdateRequest;
import com.dinoryn.worksphere.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskCreateRequest request);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(Long id, TaskUpdateRequest request);

    void deleteTask(Long id);

    List<TaskResponse> getTasksByProject(Long projectId);

    List<TaskResponse> getTasksByEmployee(Long employeeId);

    List<TaskResponse> getTasksByStatus(TaskStatus status);
}