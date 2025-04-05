package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.dto.StudentCreateDTO;
import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.service.StudentService;
import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Student> create(@RequestBody StudentCreateDTO student){
        Student newStudent = studentCreateDTOToModel(student);
        return ResponseEntity.ok(studentService.create(newStudent));
    }

    private static Student studentCreateDTOToModel(StudentCreateDTO student) {
        Student newStudent = Student.builder()
                .course(student.getCourse())
                .name(student.getName())
                .build();
        return newStudent;
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

    @GetMapping(path = "/{id}")
    public Student getById(@PathVariable Long id){

        return this.studentService.getbyId(id);
    }

    @DeleteMapping(path = "/{id}")
    public Student delete(@PathVariable Long id){
        return this.studentService.delete(id);
    }
}
