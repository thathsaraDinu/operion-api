package com.dinoryn.worksphere.service.impl;

import com.dinoryn.worksphere.dto.ProjectMemberCreateRequest;
import com.dinoryn.worksphere.dto.ProjectMemberResponse;
import com.dinoryn.worksphere.entity.Employee;
import com.dinoryn.worksphere.entity.Project;
import com.dinoryn.worksphere.entity.ProjectMember;
import com.dinoryn.worksphere.exception.EmployeeNotFoundException;
import com.dinoryn.worksphere.exception.ProjectNotFoundException;
import com.dinoryn.worksphere.mapper.ProjectMemberMapper;
import com.dinoryn.worksphere.repository.EmployeeRepository;
import com.dinoryn.worksphere.repository.ProjectMemberRepository;
import com.dinoryn.worksphere.repository.ProjectRepository;
import com.dinoryn.worksphere.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public List<ProjectMemberResponse> getProjectMembers(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));


        return project.getMembers()
                .stream()
                .map(projectMemberMapper::toResponse)
                .toList();
    }


    @Override
    public void removeMember(Long memberId) {

        ProjectMember member = projectMemberRepository.findById(memberId)
                .orElseThrow();

        projectMemberRepository.delete(member);
    }
}