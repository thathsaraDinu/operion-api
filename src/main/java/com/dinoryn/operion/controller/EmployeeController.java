package com.dinoryn.operion.controller;

import com.dinoryn.operion.dto.EmployeeCreateRequest;
import com.dinoryn.operion.dto.EmployeeResponse;
import com.dinoryn.operion.dto.EmployeeUpdateRequest;
import com.dinoryn.operion.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "Employee CRUD operations and department assignment")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    @Operation(summary = "Create employee", description = "Create a new employee account. Requires ADMIN or HR role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeCreateRequest request) {

        EmployeeResponse response = employeeService.saveEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve all employees with pagination. Accessible by all authenticated users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                employeeService.getAllEmployees(pageable)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieve a specific employee by their ID. Accessible by all authenticated users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @Parameter(description = "Employee ID") @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update employee", description = "Partially update employee information. Requires ADMIN or HR role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateRequest request
    ) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee", description = "Delete an employee by ID. Requires ADMIN or HR role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<Void> deleteEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id
    ) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PatchMapping("/{employeeId}/department/{departmentId}")
    @Operation(summary = "Assign department to employee", description = "Assign a department to an employee. Requires ADMIN or HR role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Employee or department not found"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<EmployeeResponse> assignDepartment(
            @Parameter(description = "Employee ID") @PathVariable Long employeeId,
            @Parameter(description = "Department ID") @PathVariable Long departmentId
    ){

        return ResponseEntity.ok(
                employeeService.assignDepartment(
                        employeeId,
                        departmentId
                )
        );
    }
}