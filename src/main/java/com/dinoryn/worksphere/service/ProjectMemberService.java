package com.dinoryn.worksphere.service;

import com.dinoryn.worksphere.dto.ProjectMemberCreateRequest;
import com.dinoryn.worksphere.dto.ProjectMemberResponse;

import java.util.List;

public interface ProjectMemberService {

    ProjectMemberResponse addMember(
            Long projectId,
            ProjectMemberCreateRequest request
    );

    List<ProjectMemberResponse> getProjectMembers(
            Long projectId
    );

    void removeMember(Long memberId);
}