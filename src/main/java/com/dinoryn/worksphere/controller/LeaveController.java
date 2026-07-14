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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveResponse createLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @Valid @RequestBody LeaveCreateRequest request
    ) {
        return leaveService.createLeaveRequest(
                user.getEmployee(),
                request
        );
    }

    @GetMapping("/me")
    public List<LeaveResponse> getMyLeaveRequests(
            @AuthenticationPrincipal EmployeeUserDetails user
    ) {
        return leaveService.getMyLeaveRequests(
                user.getEmployee()
        );
    }

    @GetMapping("/{leaveRequestId}")
    public LeaveResponse getLeaveRequestById(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId
    ) {
        return leaveService.getLeaveRequestById(
                user.getEmployee(),
                leaveRequestId
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping
    public List<LeaveResponse> getAllLeaveRequests(
            @AuthenticationPrincipal EmployeeUserDetails user
    ) {
        return leaveService.getAllLeaveRequests(
                user.getEmployee()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @GetMapping("/status/{status}")
    public List<LeaveResponse> getLeaveRequestsByStatus(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable LeaveStatus status
    ) {
        return leaveService.getLeaveRequestsByStatus(
                user.getEmployee(),
                status
        );
    }

    @PutMapping("/{leaveRequestId}")
    public LeaveResponse updateLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId,
            @Valid @RequestBody LeaveUpdateRequest request
    ) {

        return leaveService.updateLeaveRequest(
                user.getEmployee(),
                leaveRequestId,
                request
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @PatchMapping("/{leaveRequestId}/approval")
    public LeaveResponse approveLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId,
            @Valid @RequestBody LeaveApprovalRequest request
    ) {

        return leaveService.approveLeaveRequest(
                user.getEmployee(),
                leaveRequestId,
                request
        );
    }

    @DeleteMapping("/{leaveRequestId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeaveRequest(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long leaveRequestId
    ) {
        leaveService.deleteLeaveRequest(
                user.getEmployee(),
                leaveRequestId
        );
    }
}