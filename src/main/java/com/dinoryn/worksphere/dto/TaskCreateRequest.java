package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.TaskPriority;
import com.dinoryn.worksphere.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskCreateRequest {

    @NotBlank
    private String title;

    @NotNull
    private TaskPriority priority;

    private String description;

    @NotNull
    private TaskStatus status;

    @NotNull
    private Long projectId;

    @NotNull
    private Long assignedEmployeeId;
}