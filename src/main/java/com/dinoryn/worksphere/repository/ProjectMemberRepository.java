package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository
        extends JpaRepository<ProjectMember, Long> {

}