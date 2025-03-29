package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.repository.StudentRepository;
import br.ueg.progwebi.collegeapi.service.StudentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> listAll() {
        return repository.findAll();
    }

    @Override
    public Student getbyId(Long id) {
        return null;
    }

    @Override
    public Student create(Student student) {
        if(Strings.isEmpty(student.getName())){
            throw new RuntimeException("Name cannot be empty");
        }

        Optional<Student> checkExist = repository.findById(student.getId());
        if(checkExist.isPresent()){
            throw new RuntimeException("Student already exists");
        }
        Optional<Student> checkExist2 = repository.findByName(student.getName());
        if(checkExist2.isPresent()){
            throw new RuntimeException("Student with this name already exists");
        }
        return repository.save(student);

    }

    @Override
    public Student update(Student student) {
        if(Strings.isEmpty(student.getName()) ||
                Objects.isNull(student.getId()) ||
                student.getId().longValue()==0
        ){
            throw new RuntimeException("Information incomplete (name or ID)");
        }
        return repository.save(student);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Student> listStudentsCourse(String course) {
        Optional<List<Student>> allStudentsCourse = repository.findAllStudentsCourse(course);
        return allStudentsCourse.orElseGet(List::of);
        /* o returno acima é equivalente ao código abaixo
        if(allStudentsCourse.isPresent()){
            return allStudentsCourse.get();
        }
        return List.of();*/
    }
}
