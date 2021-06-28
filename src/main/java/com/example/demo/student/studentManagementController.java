package com.example.demo.student;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/student")
public class studentManagementController {
    private final static List<student> students=  Arrays.asList(
            new student(1, "James Bond"),
            new student(2, "Maria Jones"),
            new student(3, "Anna Smith")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_admin','ROLE_adminTraine')")
    public List<student> getAllStudents(){
        return students;
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('student_write')")
    public void AddStudent(@RequestBody student student){
        System.out.println("AddStudent "+student);
    }
    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student_write')")
    public void UpdateStudent(@PathVariable("studentId") Integer studentId,
                              @RequestBody student student){
        System.out.println(String.format("%s %s",studentId,student));
    }
    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student_write')")
    public void DeleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("DeleteStudent "+studentId);
    }
}
