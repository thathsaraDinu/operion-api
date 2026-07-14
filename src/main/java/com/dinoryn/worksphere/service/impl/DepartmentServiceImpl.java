package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.DepartmentCreateRequest;
import com.dinoryn.worksphere.dto.DepartmentResponse;
import com.dinoryn.worksphere.dto.DepartmentUpdateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.entity.Department;
import com.dinoryn.worksphere.exception.DepartmentHasEmployeesException;
import com.dinoryn.worksphere.exception.DepartmentNotFoundException;
import com.dinoryn.worksphere.mapper.DepartmentMapper;
import com.dinoryn.worksphere.mapper.EmployeeMapper;
import com.dinoryn.worksphere.repository.DepartmentRepository;
import com.dinoryn.worksphere.repository.EmployeeRepository;
import com.dinoryn.worksphere.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;


    @Override
    public DepartmentResponse saveDepartment(
            DepartmentCreateRequest request
    ){

        Department department =
                departmentMapper.toEntity(request);

        Department savedDepartment =
                departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }


    @Override
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(department -> {
                    DepartmentResponse response = departmentMapper.toResponse(department);

                    response.setEmployeeCount(
                            employeeRepository.countByDepartmentId(department.getId())
                    );

                    return response;
                })
                .toList();
    }


    @Override
    public DepartmentResponse getDepartmentById(Long id){

        Department department =
                departmentRepository.findById(id)
                        .orElseThrow(
                                () -> new DepartmentNotFoundException(id)
                        );

        return departmentMapper.toResponse(department);
    }


    @Override
    public DepartmentResponse updateDepartment(
            Long id,
            DepartmentUpdateRequest request
    ){

        Department department =
                departmentRepository.findById(id)
                        .orElseThrow(
                                () -> new DepartmentNotFoundException(id)
                        );


        department.setName(request.getName());
        department.setCode(request.getCode());
        department.setDescription(request.getDescription());


        Department updatedDepartment =
                departmentRepository.save(department);


        return departmentMapper.toResponse(updatedDepartment);
    }


    @Override
    public void deleteDepartment(Long id){

        Department department =
                departmentRepository.findById(id)
                        .orElseThrow(
                                () -> new DepartmentNotFoundException(id)
                        );


        if (employeeRepository.existsByDepartmentId(id)) {
            throw new DepartmentHasEmployeesException(id);
        }

        departmentRepository.delete(department);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(
            Long departmentId
    ){

        departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new DepartmentNotFoundException(departmentId)
                );


        return employeeRepository
                .findByDepartmentId(departmentId)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
}