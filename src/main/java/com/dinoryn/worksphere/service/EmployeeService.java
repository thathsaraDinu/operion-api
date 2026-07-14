package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.EmployeeCreateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.dto.EmployeeUpdateRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse saveEmployee(EmployeeCreateRequest request);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request);

    void deleteEmployee(Long id);

    EmployeeResponse assignDepartment(
            Long employeeId,
            Long departmentId
    );
}

