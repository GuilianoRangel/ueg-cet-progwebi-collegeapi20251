package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> listAll(){
        return studentService.listAll();
    }
}
