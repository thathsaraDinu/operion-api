package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.EmployeeCreateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.dto.EmployeeUpdateRequest;
import com.dinoryn.worksphere.entity.Department;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.exception.DepartmentNotFoundException;
import com.dinoryn.worksphere.exception.EmailAlreadyExistsException;
import com.dinoryn.worksphere.exception.EmployeeNotFoundException;
import com.dinoryn.worksphere.mapper.EmployeeMapper;
import com.dinoryn.worksphere.repository.DepartmentRepository;
import com.dinoryn.worksphere.repository.EmployeeRepository;
import com.dinoryn.worksphere.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeResponse saveEmployee(EmployeeCreateRequest request) {

        Employee employee = employeeMapper.toEntity(request);

        if(request.getDepartmentId() != null){

            Department department =
                    departmentRepository.findById(request.getDepartmentId())
                            .orElseThrow(
                                    () -> new DepartmentNotFoundException(
                                            request.getDepartmentId()
                                    )
                            );

            employee.setDepartment(department);
        }

        if(employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        employee.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {

        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toResponse);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        if (request.getFirstName() != null) {
            employee.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            employee.setLastName(request.getLastName());
        }

        if (request.getEmail() != null) {
            employee.setEmail(request.getEmail());
        }

        if (request.getPassword() != null) {
            employee.setPassword(request.getPassword());
        }

        if (request.getRole() != null) {
            employee.setRole(request.getRole());
        }

        if (request.getPhone() != null) {
            employee.setPhone(request.getPhone());
        }

        if (request.getAddress() != null) {
            employee.setAddress(request.getAddress());
        }

        if (request.getPosition() != null) {
            employee.setPosition(request.getPosition());
        }

        if (request.getJoiningDate() != null) {
            employee.setJoiningDate(request.getJoiningDate());
        }

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponse assignDepartment(
            Long employeeId,
            Long departmentId
    ){

        Employee employee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(
                                () -> new EmployeeNotFoundException(employeeId)
                        );


        Department department =
                departmentRepository.findById(departmentId)
                        .orElseThrow(
                                () -> new DepartmentNotFoundException(departmentId)
                        );


        employee.setDepartment(department);


        Employee updatedEmployee =
                employeeRepository.save(employee);


        return employeeMapper.toResponse(updatedEmployee);
    }
}