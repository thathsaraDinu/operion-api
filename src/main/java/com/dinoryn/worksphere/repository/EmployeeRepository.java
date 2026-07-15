package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByDepartmentId(
            Long departmentId,
            Pageable pageable
    );

    boolean existsByDepartmentId(Long departmentId);

    long countByDepartmentId(Long departmentId);

    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}