package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Task;
import com.dinoryn.worksphere.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByProjectId(
            Long projectId,
            Pageable pageable
    );


    Page<Task> findByAssignedEmployeeId(
            Long employeeId,
            Pageable pageable
    );


    Page<Task> findByStatus(
            TaskStatus status,
            Pageable pageable
    );
}