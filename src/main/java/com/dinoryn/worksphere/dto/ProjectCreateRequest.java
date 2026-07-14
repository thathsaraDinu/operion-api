package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private ProjectStatus status;

    private String description;

    private LocalDate endDate;

}