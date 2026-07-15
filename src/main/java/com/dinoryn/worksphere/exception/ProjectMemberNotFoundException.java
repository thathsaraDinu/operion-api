package com.dinoryn.worksphere.exception;

public class ProjectMemberNotFoundException extends RuntimeException {

    public ProjectMemberNotFoundException(Long id) {
        super("Project member not found with id: " + id);
    }
}