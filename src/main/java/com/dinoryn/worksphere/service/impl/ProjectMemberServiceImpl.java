package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.ProjectMemberCreateRequest;
import com.dinoryn.worksphere.dto.ProjectMemberResponse;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.entity.Project;
import com.dinoryn.worksphere.entity.ProjectMember;
import com.dinoryn.worksphere.exception.EmployeeNotFoundException;
import com.dinoryn.worksphere.exception.ProjectMemberNotFoundException;
import com.dinoryn.worksphere.exception.ProjectNotFoundException;
import com.dinoryn.worksphere.mapper.ProjectMemberMapper;
import com.dinoryn.worksphere.repository.EmployeeRepository;
import com.dinoryn.worksphere.repository.ProjectMemberRepository;
import com.dinoryn.worksphere.repository.ProjectRepository;
import com.dinoryn.worksphere.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectMemberMapper projectMemberMapper;


    @Override
    public ProjectMemberResponse addMember(
            Long projectId,
            ProjectMemberCreateRequest request
    ) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));


        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(request.getEmployeeId()));


        ProjectMember member = new ProjectMember();

        member.setProject(project);
        member.setEmployee(employee);
        member.setProjectRole(request.getProjectRole());
        member.setAssignedDate(LocalDate.now());


        ProjectMember savedMember =
                projectMemberRepository.save(member);


        return projectMemberMapper.toResponse(savedMember);
    }


    @Override
    public Page<ProjectMemberResponse> getProjectMembers(
            Long projectId,
            Pageable pageable
    ) {

        projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new ProjectNotFoundException(projectId)
                );


        return projectMemberRepository
                .findByProjectId(
                        projectId,
                        pageable
                )
                .map(projectMemberMapper::toResponse);
    }


    @Override
    public void removeMember(
            Long projectId,
            Long memberId
    ) {

        ProjectMember member = projectMemberRepository.findById(memberId)
                .orElseThrow(
                        () -> new ProjectMemberNotFoundException(memberId)
                );


        if (!member.getProject().getId().equals(projectId)) {
            throw new ProjectMemberNotFoundException(memberId);
        }


        projectMemberRepository.delete(member);
    }
}