package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.ProjectCreateRequest;
import com.dinoryn.worksphere.dto.ProjectResponse;
import com.dinoryn.worksphere.dto.ProjectUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProjectService {

    ProjectResponse createProject(ProjectCreateRequest request);

    ProjectResponse getProjectById(Long id);

    Page<ProjectResponse> getAllProjects(Pageable pageable);

    ProjectResponse updateProject(Long id, ProjectUpdateRequest request);

    void deleteProject(Long id);
}