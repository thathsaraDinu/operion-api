package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.ProjectMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository
        extends JpaRepository<ProjectMember, Long> {

    Page<ProjectMember> findByProjectId(
            Long projectId,
            Pageable pageable
    );

}