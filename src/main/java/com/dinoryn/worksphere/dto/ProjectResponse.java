package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProjectResponse {

    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private ProjectStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}