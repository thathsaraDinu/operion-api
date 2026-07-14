package com.dinoryn.worksphere.mapper;

import com.dinoryn.worksphere.dto.ProjectCreateRequest;
import com.dinoryn.worksphere.dto.ProjectResponse;
import com.dinoryn.worksphere.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectCreateRequest request) {

        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setStatus(request.getStatus());

        return project;
    }


    public ProjectResponse toResponse(Project project) {

        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setStartDate(project.getStartDate());
        response.setEndDate(project.getEndDate());
        response.setStatus(project.getStatus());

        return response;
    }
}