package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.LeaveRequest;
import com.dinoryn.worksphere.entity.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    Page<LeaveRequest> findByEmployeeId(
            Long employeeId,
            Pageable pageable
    );

    Page<LeaveRequest> findByStatus(
            LeaveStatus status,
            Pageable pageable
    );

    Optional<LeaveRequest> findByIdAndEmployeeId(
            Long leaveRequestId,
            Long employeeId
    );
}