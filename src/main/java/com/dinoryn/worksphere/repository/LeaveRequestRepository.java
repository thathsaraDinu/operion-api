package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.LeaveRequest;
import com.dinoryn.worksphere.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

}