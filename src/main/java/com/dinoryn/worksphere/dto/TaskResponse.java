package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.TaskPriority;
import com.dinoryn.worksphere.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private TaskPriority priority;

    private TaskStatus status;

    private LocalDate createdDate;

    private Long projectId;

    private String projectName;

    private Long assignedEmployeeId;

    private String assignedEmployeeName;
}