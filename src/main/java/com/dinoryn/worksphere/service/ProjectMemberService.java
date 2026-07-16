package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.ProjectMemberCreateRequest;
import com.dinoryn.worksphere.dto.ProjectMemberResponse;
import com.dinoryn.worksphere.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProjectMemberService {

    ProjectMemberResponse addMember(
            Long projectId,
            ProjectMemberCreateRequest request
    );

    Page<ProjectMemberResponse> getProjectMembers(
            Long projectId,
            Pageable pageable
    );

    void removeMember(Long projectId, Long memberId);
}