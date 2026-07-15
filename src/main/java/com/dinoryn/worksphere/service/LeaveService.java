package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.LeaveApprovalRequest;
import com.dinoryn.worksphere.dto.LeaveCreateRequest;
import com.dinoryn.worksphere.dto.LeaveResponse;
import com.dinoryn.worksphere.dto.LeaveUpdateRequest;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.entity.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LeaveService {

    LeaveResponse createLeaveRequest(
            Employee employee,
            LeaveCreateRequest request
    );

    LeaveResponse updateLeaveRequest(
            Employee employee, Long leaveRequestId,
            LeaveUpdateRequest request
    );

    LeaveResponse approveLeaveRequest(
            Employee employee,
            Long leaveRequestId,
            LeaveApprovalRequest request
    );

    LeaveResponse getLeaveRequestById(Employee employee, Long leaveRequestId);

    Page<LeaveResponse> getAllLeaveRequests(
            Pageable pageable
    );

    Page<LeaveResponse> getMyLeaveRequests(
            Employee employee,
            Pageable pageable
    );

    Page<LeaveResponse> getLeaveRequestsByStatus(
            LeaveStatus status,
            Pageable pageable
    );

    void deleteLeaveRequest(
            Employee employee,
            Long leaveRequestId
    );
}