package com.example.demo.student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class studentController {

    private static  List<student> STUDENTS= Arrays.asList(
            new student(1, "james Bond"),
            new student(2,"Maria Jones"),
            new student(3,"anna smith")
    );


    @GetMapping(path = "{student_id}")
    public student getStudent(@PathVariable("student_id") Integer studentId){
        return STUDENTS.stream().filter(student ->  studentId.equals(student.getId()))
                .findFirst()
                .orElseThrow( () ->new IllegalStateException("students with id "+studentId+"does not exists"));
    }
}
