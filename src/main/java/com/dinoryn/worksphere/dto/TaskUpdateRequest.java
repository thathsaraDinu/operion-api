package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.TaskPriority;
import com.dinoryn.worksphere.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskUpdateRequest {

    private String title;

    private TaskPriority priority;

    private String description;

    private TaskStatus status;
    
}