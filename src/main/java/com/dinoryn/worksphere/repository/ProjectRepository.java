package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}