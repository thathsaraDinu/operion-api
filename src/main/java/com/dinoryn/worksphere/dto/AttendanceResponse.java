package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.AttendanceStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private LocalDate date;

    private LocalDateTime clockIn;

    private LocalDateTime clockOut;

    private AttendanceStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}