package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.DepartmentCreateRequest;
import com.dinoryn.worksphere.dto.DepartmentResponse;
import com.dinoryn.worksphere.dto.DepartmentUpdateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {


    private final DepartmentService departmentService;


    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentCreateRequest request
    ){

        DepartmentResponse response =
                departmentService.saveDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(){

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentUpdateRequest request
    ){

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id
    ){

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeResponse>> getDepartmentEmployees(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                departmentService.getEmployeesByDepartment(id)
        );
    }
}