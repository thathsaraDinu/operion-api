package com.dinoryn.worksphere.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceUpdateRequest {

    private LocalDateTime clockIn;

    private LocalDateTime clockOut;
}