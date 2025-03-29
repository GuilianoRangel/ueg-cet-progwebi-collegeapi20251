package br.ueg.progwebi.collegeapi.repository;

import br.ueg.progwebi.collegeapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByName(String name);

    //JPQL
    @Query("from Student s where s.course = :course")
    Optional<List<Student>> findAllStudentsCourse(String course);
}
