package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.DepartmentCreateRequest;
import com.dinoryn.worksphere.dto.DepartmentResponse;
import com.dinoryn.worksphere.dto.DepartmentUpdateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentCreateRequest request
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.saveDepartment(request));
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<Page<DepartmentResponse>> getAllDepartments(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                departmentService.getAllDepartments(pageable)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentUpdateRequest request
    ){

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request)
        );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id
    ){

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/{id}/employees")
    public ResponseEntity<Page<EmployeeResponse>> getDepartmentEmployees(
            @PathVariable Long id,
            Pageable pageable
    ){

        return ResponseEntity.ok(
                departmentService.getEmployeesByDepartment(
                        id,
                        pageable
                )
        );
    }
}