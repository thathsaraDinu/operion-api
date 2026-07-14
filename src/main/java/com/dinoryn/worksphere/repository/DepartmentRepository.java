package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

}