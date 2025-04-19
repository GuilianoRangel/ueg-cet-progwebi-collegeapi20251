package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.dto.StudentDataDTO;
import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Student> create(@RequestBody StudentDataDTO student){
        Student newStudent = studentDTOToModel(student);
        return ResponseEntity.ok(studentService.create(newStudent));
    }

    private static Student studentDTOToModel(StudentDataDTO student) {
        Student newStudent = Student.builder()
                .course(student.getCourse())
                .name(student.getName())
                .build();
        return newStudent;
    }

    @PutMapping(path = "/{id}")
    public Student update(
            @PathVariable Long id,
            @RequestBody StudentDataDTO studentDTO){
        Student studentUpdate = studentDTOToModel(studentDTO);
        return studentService.update(id, studentUpdate);
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
