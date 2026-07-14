package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveUpdateRequest {

    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;
}