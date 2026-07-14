package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeUpdateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String phone;

    private String address;

    private String position;

    private LocalDate joiningDate;
}