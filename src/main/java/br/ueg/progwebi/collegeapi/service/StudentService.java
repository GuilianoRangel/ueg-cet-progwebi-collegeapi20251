package br.ueg.progwebi.collegeapi.service;

import br.ueg.progwebi.collegeapi.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    Student getbyId(Long id);
    Student create(Student student);
    Student update(Student student);
    void delete(Long id);
    List<Student> listStudentsCourse(String course);
}
