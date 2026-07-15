package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.ProjectRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProjectMemberResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private Long projectId;

    @Enumerated(EnumType.STRING)
    private ProjectRole projectRole;

    private LocalDate assignedDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}