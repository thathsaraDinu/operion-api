package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.ProjectMemberCreateRequest;
import com.dinoryn.worksphere.dto.ProjectMemberResponse;
import com.dinoryn.worksphere.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;


    @PostMapping
    public ResponseEntity<ProjectMemberResponse> addMember(
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectMemberCreateRequest request
    ) {

        return new ResponseEntity<>(
                projectMemberService.addMember(projectId, request),
                HttpStatus.CREATED
        );
    }


    @GetMapping
    public ResponseEntity<List<ProjectMemberResponse>> getMembers(
            @PathVariable Long projectId
    ) {

        return ResponseEntity.ok(
                projectMemberService.getProjectMembers(projectId)
        );
    }


    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ) {

        projectMemberService.removeMember(memberId);

        return ResponseEntity.noContent().build();
    }
}