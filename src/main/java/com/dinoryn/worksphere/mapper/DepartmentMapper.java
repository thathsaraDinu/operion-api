package com.dinoryn.worksphere.mapper;

import com.dinoryn.worksphere.dto.DepartmentCreateRequest;
import com.dinoryn.worksphere.dto.DepartmentResponse;
import com.dinoryn.worksphere.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {


    public Department toEntity(DepartmentCreateRequest request){

        Department department = new Department();

        department.setName(request.getName());
        department.setCode(request.getCode());
        department.setDescription(request.getDescription());

        return department;
    }


    public DepartmentResponse toResponse(Department department){

        DepartmentResponse response = new DepartmentResponse();

        response.setId(department.getId());
        response.setName(department.getName());
        response.setCode(department.getCode());
        response.setDescription(department.getDescription());

        return response;
    }
}