package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.ProjectCreateRequest;
import com.dinoryn.worksphere.dto.ProjectResponse;
import com.dinoryn.worksphere.dto.ProjectUpdateRequest;
import com.dinoryn.worksphere.entity.Project;
import com.dinoryn.worksphere.exception.ProjectNotFoundException;
import com.dinoryn.worksphere.mapper.ProjectMapper;
import com.dinoryn.worksphere.repository.ProjectRepository;
import com.dinoryn.worksphere.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    @Override
    public ProjectResponse createProject(ProjectCreateRequest request) {

        Project project = projectMapper.toEntity(request);

        Project savedProject = projectRepository.save(project);

        return projectMapper.toResponse(savedProject);
    }


    @Override
    public ProjectResponse getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        return projectMapper.toResponse(project);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ProjectResponse> getAllProjects(
            Pageable pageable
    ) {

        return projectRepository.findAll(pageable)
                .map(projectMapper::toResponse);
    }


    @Override
    public ProjectResponse updateProject(Long id, ProjectUpdateRequest request) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));


        if (request.getName() != null) {
            project.setName(request.getName());
        }

        if (request.getDescription() != null) {
            project.setDescription(request.getDescription());
        }

        if (request.getStartDate() != null) {
            project.setStartDate(request.getStartDate());
        }

        if (request.getEndDate() != null) {
            project.setEndDate(request.getEndDate());
        }

        if (request.getStatus() != null) {
            project.setStatus(request.getStatus());
        }


        Project updatedProject = projectRepository.save(project);

        return projectMapper.toResponse(updatedProject);
    }


    @Override
    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        projectRepository.delete(project);
    }
}