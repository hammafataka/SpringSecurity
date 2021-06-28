package com.example.demo.student;

import java.util.List;

public class student {
    private final Integer id;
    private final String studentName;

    public student(Integer id,
                   String studentName) {
        this.id = id;
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
