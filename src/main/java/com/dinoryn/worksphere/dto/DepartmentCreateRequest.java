package com.dinoryn.worksphere.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private String description;
}