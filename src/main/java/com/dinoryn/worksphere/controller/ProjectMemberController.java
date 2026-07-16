package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.ProjectMemberCreateRequest;
import com.dinoryn.worksphere.dto.ProjectMemberResponse;
import com.dinoryn.worksphere.security.EmployeeUserDetails;
import com.dinoryn.worksphere.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @PostMapping
    public ResponseEntity<ProjectMemberResponse> addMember(
            @AuthenticationPrincipal EmployeeUserDetails user,
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectMemberCreateRequest request
    ) {

        return new ResponseEntity<>(
                projectMemberService.addMember(
                        projectId,
                        request
                ),
                HttpStatus.CREATED
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<Page<ProjectMemberResponse>> getProjectMembers(
            @PathVariable Long projectId,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                projectMemberService.getProjectMembers(
                        projectId,
                        pageable
                )
        );
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'HR', 'MANAGER')")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ) {

        projectMemberService.removeMember(
                projectId,
                memberId
        );

        return ResponseEntity.noContent().build();
    }
}