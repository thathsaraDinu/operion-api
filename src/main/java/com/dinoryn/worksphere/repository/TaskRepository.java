package com.dinoryn.worksphere.repository;

import com.dinoryn.worksphere.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}