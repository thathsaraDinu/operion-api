package com.dinoryn.worksphere.exception;

public class DepartmentHasEmployeesException extends RuntimeException {

    public DepartmentHasEmployeesException(Long id) {
        super("Cannot delete department with id " + id +
                " because employees are assigned to it.");
    }
}