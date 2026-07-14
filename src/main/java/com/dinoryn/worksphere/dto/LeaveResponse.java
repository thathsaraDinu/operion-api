package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.LeaveStatus;
import com.dinoryn.worksphere.entity.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private LeaveStatus status;

    private Long approvedById;

    private String approvedByName;

    private LocalDate createdDate;
}