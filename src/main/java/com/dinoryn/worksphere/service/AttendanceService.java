package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.AttendanceResponse;
import com.dinoryn.worksphere.dto.AttendanceUpdateRequest;
import com.dinoryn.worksphere.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AttendanceService {

    AttendanceResponse clockIn(Employee employee);

    AttendanceResponse clockOut(Employee employee);

    Page<AttendanceResponse> getMyAttendance(
            Employee employee,
            Pageable pageable
    );


    Page<AttendanceResponse> getAllAttendance(
            Pageable pageable
    );


    Page<AttendanceResponse> getEmployeeAttendance(
            Long employeeId,
            Pageable pageable
    );

    AttendanceResponse updateAttendance(
            Long attendanceId,
            AttendanceUpdateRequest request
    );
}