package com.dinoryn.worksphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    private LocalDate assignedDate;


    @Enumerated(EnumType.STRING)
    private ProjectRole projectRole;
}