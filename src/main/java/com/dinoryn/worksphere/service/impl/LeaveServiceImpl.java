package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.LeaveApprovalRequest;
import com.dinoryn.worksphere.dto.LeaveCreateRequest;
import com.dinoryn.worksphere.dto.LeaveResponse;
import com.dinoryn.worksphere.dto.LeaveUpdateRequest;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.entity.LeaveRequest;
import com.dinoryn.worksphere.entity.LeaveStatus;
import com.dinoryn.worksphere.entity.Role;
import com.dinoryn.worksphere.exception.InvalidLeaveRequestException;
import com.dinoryn.worksphere.exception.LeaveRequestNotFoundException;
import com.dinoryn.worksphere.exception.UnauthorizedOperationException;
import com.dinoryn.worksphere.mapper.LeaveMapper;
import com.dinoryn.worksphere.repository.LeaveRequestRepository;
import com.dinoryn.worksphere.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveMapper leaveMapper;

    @Override
    public LeaveResponse createLeaveRequest(
            Employee employee,
            LeaveCreateRequest request
    ) {

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new InvalidLeaveRequestException(
                    "End date cannot be before start date."
            );
        }

        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(request.getLeaveType());
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setReason(request.getReason());

        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setCreatedDate(LocalDate.now());

        return leaveMapper.toResponse(
                leaveRequestRepository.save(leaveRequest)
        );
    }

    @Override
    public LeaveResponse updateLeaveRequest(
            Employee employee,
            Long leaveRequestId,
            LeaveUpdateRequest request
    ) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                        .orElseThrow(() ->
                                new LeaveRequestNotFoundException(leaveRequestId));

        if (!leaveRequest.getEmployee().getId()
                .equals(employee.getId())) {

            throw new UnauthorizedOperationException();
        }

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {

            throw new InvalidLeaveRequestException(
                    "Only pending leave requests can be updated."
            );
        }

        if (request.getEndDate()
                .isBefore(request.getStartDate())) {

            throw new InvalidLeaveRequestException(
                    "End date cannot be before start date."
            );
        }

        leaveRequest.setLeaveType(request.getLeaveType());
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setReason(request.getReason());

        LeaveRequest updatedLeaveRequest =
                leaveRequestRepository.save(leaveRequest);

        return leaveMapper.toResponse(updatedLeaveRequest);
    }

    @Override
    public LeaveResponse approveLeaveRequest(
            Employee employee,
            Long leaveRequestId,
            LeaveApprovalRequest request
    ) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                        .orElseThrow(() ->
                                new LeaveRequestNotFoundException(leaveRequestId));

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {

            throw new InvalidLeaveRequestException(
                    "Only pending leave requests can be approved or rejected."
            );
        }

        if (request.getStatus() == LeaveStatus.PENDING) {

            throw new InvalidLeaveRequestException(
                    "Approval status must be APPROVED or REJECTED."
            );
        }

        leaveRequest.setStatus(request.getStatus());

        leaveRequest.setApprovedBy(employee);

        LeaveRequest savedLeaveRequest =
                leaveRequestRepository.save(leaveRequest);

        return leaveMapper.toResponse(savedLeaveRequest);
    }

    @Override
    public LeaveResponse getLeaveRequestById(
            Employee employee,
            Long leaveRequestId
    ) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new LeaveRequestNotFoundException(leaveRequestId));

        boolean isOwner = leaveRequest.getEmployee()
                .getId()
                .equals(employee.getId());

        boolean hasElevatedRole =
                employee.getRole() == Role.ADMIN
                        || employee.getRole() == Role.HR
                        || employee.getRole() == Role.MANAGER;

        if (!isOwner && !hasElevatedRole) {
            throw new UnauthorizedOperationException();
        }

        return leaveMapper.toResponse(leaveRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeaveResponse> getAllLeaveRequests(
            Pageable pageable
    ) {

        return leaveRequestRepository.findAll(pageable)
                .map(leaveMapper::toResponse);
    }

    @Override
    public Page<LeaveResponse> getMyLeaveRequests(
            Employee employee,
            Pageable pageable
    ) {

        return leaveRequestRepository
                .findByEmployeeId(
                        employee.getId(),
                        pageable
                )
                .map(leaveMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeaveResponse> getLeaveRequestsByStatus(
            LeaveStatus status,
            Pageable pageable
    ) {

        return leaveRequestRepository.findByStatus(
                        status,
                        pageable
                )
                .map(leaveMapper::toResponse);
    }

    @Override
    public void deleteLeaveRequest(
            Employee employee,
            Long leaveRequestId
    ) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new LeaveRequestNotFoundException(leaveRequestId));

        if (!leaveRequest.getEmployee().getId().equals(employee.getId())) {
            throw new UnauthorizedOperationException();
        }

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new InvalidLeaveRequestException(
                    "Only pending leave requests can be deleted."
            );
        }

        leaveRequestRepository.delete(leaveRequest);
    }

    // ...
}
