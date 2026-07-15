package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.ProjectCreateRequest;
import com.dinoryn.worksphere.dto.ProjectResponse;
import com.dinoryn.worksphere.dto.ProjectUpdateRequest;
import com.dinoryn.worksphere.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectCreateRequest request
    ) {
        return new ResponseEntity<>(
                projectService.createProject(request),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                projectService.getProjectById(id)
        );
    }


    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getAllProjects(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                projectService.getAllProjects(
                        pageable
                )
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectUpdateRequest request
    ) {
        return ResponseEntity.ok(
                projectService.updateProject(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id
    ) {
        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }
}