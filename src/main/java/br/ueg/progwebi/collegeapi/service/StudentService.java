package br.ueg.progwebi.collegeapi.service;

import br.ueg.progwebi.collegeapi.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    Student getbyId(int id);
    Student create(Student student);
    Student update(Student student);
    void delete(int id);
}
