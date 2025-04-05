package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.controller.exceptions.ResourceNotFoundException;
import br.ueg.progwebi.collegeapi.dto.StudentCreateDTO;
import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.service.StudentService;
import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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
    public Student create(@RequestBody StudentCreateDTO student){
        Student newStudent = studentCreateDTOToModel(student);
        Student returnStudent;
        try {
            returnStudent = studentService.create(newStudent);
        }catch (BusinessException e){
            throw new ResponseStatusException(e.getCodeError(), e.getMessage(),e);
        }
        return returnStudent;
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
        Student student = this.studentService.getbyId(id);
        // Essa solução tem um problem de responsabilidade.
        // visto que a definição da mensagem deveria ser do service e não
        // da camada de visão.
        if(Objects.isNull(student)){
            throw new ResourceNotFoundException(
                    "Cliente não localizado");
        }
        return student;
    }

    @DeleteMapping(path = "/{id}")
    public Student delete(@PathVariable Long id){
        Student student;
        try{
            student = this.studentService.delete(id);
        }catch (BusinessException e){
            throw new ResponseStatusException(e.getCodeError(), e.getMessage(),e);
        }
        return student;
    }
}
