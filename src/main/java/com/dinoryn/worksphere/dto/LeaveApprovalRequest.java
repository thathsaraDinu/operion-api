package com.dinoryn.worksphere.dto;

import com.dinoryn.worksphere.entity.LeaveStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveApprovalRequest {

    @NotNull
    private LeaveStatus status;
}