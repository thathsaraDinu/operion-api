package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.TaskCreateRequest;
import com.dinoryn.worksphere.dto.TaskResponse;
import com.dinoryn.worksphere.dto.TaskUpdateRequest;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.entity.Project;
import com.dinoryn.worksphere.entity.Task;
import com.dinoryn.worksphere.exception.EmployeeNotFoundException;
import com.dinoryn.worksphere.exception.ProjectNotFoundException;
import com.dinoryn.worksphere.exception.TaskNotFoundException;
import com.dinoryn.worksphere.mapper.TaskMapper;
import com.dinoryn.worksphere.repository.EmployeeRepository;
import com.dinoryn.worksphere.repository.ProjectRepository;
import com.dinoryn.worksphere.repository.TaskRepository;
import com.dinoryn.worksphere.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskResponse createTask(TaskCreateRequest request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(request.getProjectId()));


        Employee employee = employeeRepository.findById(request.getAssignedEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(request.getAssignedEmployeeId()));


        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setCreatedDate(LocalDate.now());

        task.setProject(project);
        task.setAssignedEmployee(employee);


        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponse(savedTask);
    }


    @Override
    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return taskMapper.toResponse(task);
    }


    @Override
    public List<TaskResponse> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }


    @Override
    public TaskResponse updateTask(Long id, TaskUpdateRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));


        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());


        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponse(updatedTask);
    }


    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);
    }
}