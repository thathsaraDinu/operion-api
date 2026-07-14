package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.LeaveApprovalRequest;
import com.dinoryn.worksphere.dto.LeaveCreateRequest;
import com.dinoryn.worksphere.dto.LeaveResponse;
import com.dinoryn.worksphere.dto.LeaveUpdateRequest;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.entity.LeaveStatus;

import java.util.List;

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

    List<LeaveResponse> getAllLeaveRequests(Employee employee);

    List<LeaveResponse> getMyLeaveRequests(Employee employee);

    List<LeaveResponse> getLeaveRequestsByStatus(
            Employee employee, LeaveStatus status
    );

    void deleteLeaveRequest(
            Employee employee,
            Long leaveRequestId
    );
}