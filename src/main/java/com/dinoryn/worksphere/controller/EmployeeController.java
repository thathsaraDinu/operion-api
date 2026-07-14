package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.EmployeeCreateRequest;
import com.dinoryn.worksphere.dto.EmployeeResponse;
import com.dinoryn.worksphere.dto.EmployeeUpdateRequest;
import com.dinoryn.worksphere.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeCreateRequest request) {

        EmployeeResponse response = employeeService.saveEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeUpdateRequest request) {

        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

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