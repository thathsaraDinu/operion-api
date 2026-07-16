package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.EmployeeCreateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.dto.EmployeeUpdateRequest;
import com.dinoryn.worksphere.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeCreateRequest request) {

        EmployeeResponse response = employeeService.saveEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                employeeService.getAllEmployees(pageable)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateRequest request
    ) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PatchMapping("/{employeeId}/department/{departmentId}")
    public ResponseEntity<EmployeeResponse> assignDepartment(
            @PathVariable Long employeeId,
            @PathVariable Long departmentId
    ){

        return ResponseEntity.ok(
                employeeService.assignDepartment(
                        employeeId,
                        departmentId
                )
        );
    }
}