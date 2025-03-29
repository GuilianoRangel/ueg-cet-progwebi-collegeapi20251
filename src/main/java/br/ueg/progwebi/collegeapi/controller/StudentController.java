package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Student create(@RequestBody Student student){
        return studentService.create(student);
    }

    @PostMapping(path = "/{id}")
    public Student update(
            @PathVariable Long id,
            @RequestBody Student student){
        student.setId(id);
        return studentService.update(student);
    }

    @GetMapping(path = "/course/{course}")
    public List<Student> course(@PathVariable String course){
        return studentService.listStudentsCourse(course);
    }
}
