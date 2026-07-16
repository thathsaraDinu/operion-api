package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.LeaveApprovalRequest;
import com.dinoryn.worksphere.dto.LeaveCreateRequest;
import com.dinoryn.worksphere.dto.LeaveResponse;
import com.dinoryn.worksphere.dto.LeaveUpdateRequest;
import com.dinoryn.worksphere.entity.LeaveStatus;
import com.dinoryn.worksphere.security.EmployeeUserDetails;
import com.dinoryn.worksphere.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;


    @PostMapping
    public ResponseEntity<LeaveResponse> createLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @Valid @RequestBody LeaveCreateRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        leaveService.createLeaveRequest(
                                user.employee(),
                                request
                        )
                );
    }


    @GetMapping("/me")
    public ResponseEntity<Page<LeaveResponse>> getMyLeaveRequests(
            @AuthenticationPrincipal EmployeeUserDetails user,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                leaveService.getMyLeaveRequests(
                        user.employee(),
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/{leaveRequestId:\\d+}")
    public ResponseEntity<LeaveResponse> getLeaveRequestById(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId
    ) {

        return ResponseEntity.ok(
                leaveService.getLeaveRequestById(
                        user.employee(),
                        leaveRequestId
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping
    public ResponseEntity<Page<LeaveResponse>> getAllLeaveRequests(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                leaveService.getAllLeaveRequests(pageable)
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<LeaveResponse>> getLeaveRequestsByStatus(
            @PathVariable LeaveStatus status,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                leaveService.getLeaveRequestsByStatus(
                        status,
                        pageable
                )
        );
    }


    @PutMapping("/{leaveRequestId}")
    public ResponseEntity<LeaveResponse> updateLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId,
            @Valid @RequestBody LeaveUpdateRequest request
    ) {

        return ResponseEntity.ok(
                leaveService.updateLeaveRequest(
                        user.employee(),
                        leaveRequestId,
                        request
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @PatchMapping("/{leaveRequestId}/approval")
    public ResponseEntity<LeaveResponse> approveLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId,
            @Valid @RequestBody LeaveApprovalRequest request
    ) {

        return ResponseEntity.ok(
                leaveService.approveLeaveRequest(
                        user.employee(),
                        leaveRequestId,
                        request
                )
        );
    }


    @DeleteMapping("/{leaveRequestId}")
    public ResponseEntity<Void> deleteLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId
    ) {

        leaveService.deleteLeaveRequest(
                user.employee(),
                leaveRequestId
        );

        return ResponseEntity.noContent().build();
    }
}