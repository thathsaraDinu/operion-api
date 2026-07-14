package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.TaskCreateRequest;
import com.dinoryn.worksphere.dto.TaskResponse;
import com.dinoryn.worksphere.dto.TaskUpdateRequest;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskCreateRequest request);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(Long id, TaskUpdateRequest request);

    void deleteTask(Long id);
}