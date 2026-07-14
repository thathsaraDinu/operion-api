package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.DepartmentCreateRequest;
import com.dinoryn.worksphere.dto.DepartmentResponse;
import com.dinoryn.worksphere.dto.DepartmentUpdateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse saveDepartment(DepartmentCreateRequest request);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse updateDepartment(
            Long id,
            DepartmentUpdateRequest request
    );

    void deleteDepartment(Long id);

    List<EmployeeResponse> getEmployeesByDepartment(Long departmentId);

}