package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> listAll() {
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
        List<Student> students = Arrays.asList(student, student2);
        return students;
    }

    @Override
    public Student getbyId(int id) {
        return null;
    }

    @Override
    public Student create(Student student) {
        return null;
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
