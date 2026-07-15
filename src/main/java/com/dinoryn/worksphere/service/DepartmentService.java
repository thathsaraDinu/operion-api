package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.DepartmentCreateRequest;
import com.dinoryn.worksphere.dto.DepartmentResponse;
import com.dinoryn.worksphere.dto.DepartmentUpdateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    DepartmentResponse saveDepartment(
            DepartmentCreateRequest request
    );


    Page<DepartmentResponse> getAllDepartments(
            Pageable pageable
    );


    DepartmentResponse getDepartmentById(
            Long id
    );


    DepartmentResponse updateDepartment(
            Long id,
            DepartmentUpdateRequest request
    );


    void deleteDepartment(
            Long id
    );


    Page<EmployeeResponse> getEmployeesByDepartment(
            Long departmentId,
            Pageable pageable
    );
}