package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.EmployeeStatus;
import com.dinoryn.worksphere.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    private Long departmentId;

    private String departmentName;

    private String phone;

    private String address;

    private String position;

    private LocalDate joiningDate;

    private EmployeeStatus status;
}