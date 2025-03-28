package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @GetMapping
    public List<Student> listAll(){
        Student student = new Student();
        student.setId(1L);
        student.setName("Student 1");
        student.setRegisterNumber("2025010101");
        student.setCourse("SI");
        student.setRegisterDate(LocalDate.now());

        Student student2 = Student.builder()
                .id(2L)
                .name("Student 2")
                .registerNumber("2025010102")
                .course("SI")
                .registerDate(LocalDate.now())
                .build();
        List<Student> students =Arrays.asList(student, student2);
        return students;
    }
}
