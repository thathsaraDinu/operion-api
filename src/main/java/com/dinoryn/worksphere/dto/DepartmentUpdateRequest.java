package com.dinoryn.worksphere.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateRequest {

    private String name;

    private String code;

    private String description;
}