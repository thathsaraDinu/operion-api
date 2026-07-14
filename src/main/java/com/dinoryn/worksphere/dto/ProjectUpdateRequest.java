package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectUpdateRequest {

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private ProjectStatus status;
}