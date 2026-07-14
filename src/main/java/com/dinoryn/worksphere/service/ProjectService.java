package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.ProjectCreateRequest;
import com.dinoryn.worksphere.dto.ProjectResponse;
import com.dinoryn.worksphere.dto.ProjectUpdateRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectCreateRequest request);

    ProjectResponse getProjectById(Long id);

    List<ProjectResponse> getAllProjects();

    ProjectResponse updateProject(Long id, ProjectUpdateRequest request);

    void deleteProject(Long id);
}