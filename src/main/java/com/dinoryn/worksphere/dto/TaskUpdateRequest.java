package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.TaskPriority;
import com.dinoryn.worksphere.entity.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Task update request payload")
public class TaskUpdateRequest {

    @Schema(description = "Task title", example = "Implement user authentication")
    private String title;

    @Schema(description = "Task priority", example = "HIGH")
    private TaskPriority priority;

    @Schema(description = "Task description", example = "Implement JWT-based authentication for the application")
    private String description;

    @Schema(description = "Task status", example = "IN_PROGRESS")
    private TaskStatus status;
    
}