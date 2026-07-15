package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.AttendanceResponse;
import com.dinoryn.worksphere.dto.AttendanceUpdateRequest;
import com.dinoryn.worksphere.security.EmployeeUserDetails;
import com.dinoryn.worksphere.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;


    @PostMapping("/clock-in")
    public ResponseEntity<AttendanceResponse> clockIn(
            @AuthenticationPrincipal EmployeeUserDetails user
    ) {

        return ResponseEntity.ok(
                attendanceService.clockIn(
                        user.employee()
                )
        );
    }


    @PatchMapping("/clock-out")
    public ResponseEntity<AttendanceResponse> clockOut(
            @AuthenticationPrincipal EmployeeUserDetails user
    ) {

        return ResponseEntity.ok(
                attendanceService.clockOut(
                        user.employee()
                )
        );
    }


    @GetMapping("/me")
    public ResponseEntity<Page<AttendanceResponse>> getMyAttendance(
            @AuthenticationPrincipal EmployeeUserDetails user,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                attendanceService.getMyAttendance(
                        user.employee(),
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping
    public ResponseEntity<Page<AttendanceResponse>> getAllAttendance(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance(
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Page<AttendanceResponse>> getEmployeeAttendance(
            @PathVariable Long employeeId,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                attendanceService.getEmployeeAttendance(
                        employeeId,
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceResponse> updateAttendance(
            @PathVariable Long attendanceId,
            @Valid @RequestBody AttendanceUpdateRequest request
    ) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(
                        attendanceId,
                        request
                )
        );
    }
}