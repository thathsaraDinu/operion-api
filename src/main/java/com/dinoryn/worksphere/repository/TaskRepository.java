package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Task;
import com.dinoryn.worksphere.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    List<Task> findByAssignedEmployeeId(Long employeeId);

    List<Task> findByStatus(TaskStatus status);
}