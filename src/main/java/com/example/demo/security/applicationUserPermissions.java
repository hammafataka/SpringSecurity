package com.example.demo.security;

public enum applicationUserPermissions {
    student_read("student_read"),
    student_write("student_write"),
    course_read("course_read"),
    course_write("course_write");
    private final String permissions;

    applicationUserPermissions(String permissions) {
        this.permissions=permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
