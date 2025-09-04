package br.ueg.progwebi.collegeapi.service;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.repository.StudentRepository;

import java.util.List;

public interface StudentService
        extends CrudGenericService<Student, Long, StudentRepository> {
    List<Student> listStudentsCourse(String course);
}
