package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    boolean existsByDepartmentId(Long departmentId);

    long countByDepartmentId(Long departmentId);

    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}