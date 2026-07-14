package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.ProjectRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectMemberCreateRequest {

    @NotNull
    private Long employeeId;

    @NotNull
    private ProjectRole projectRole;
}