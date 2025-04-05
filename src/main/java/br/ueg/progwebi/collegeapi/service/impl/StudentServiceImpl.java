package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.repository.StudentRepository;
import br.ueg.progwebi.collegeapi.service.StudentService;
import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Optional<Student> student = this.repository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }

    @Override
    public Student create(Student student) {
        createValidation(student);
        prepareToCreate(student);
        return repository.save(student);

    }

    private Student prepareToCreate(Student student) {
        student.setRegisterDate(LocalDate.now());
        student.setRegisterNumber(getNewRegisterNumberStudent());
        return student;
    }

    private String getNewRegisterNumberStudent() {
        Optional<Student> topByOrderByIdDesc = this.repository.findTopByOrderByIdDesc();
        Student topStudent = topByOrderByIdDesc.orElse(Student.builder().id(0L).build());
        Long newId = topStudent.getId()+1;
        Integer year = LocalDate.now().getYear();
        return year.toString().concat(newId.toString());
    }

    private void createValidation(Student student) {
        if(Strings.isEmpty(student.getName())){
            throw new BusinessException("Name não pode ser nulo ou vazio");
        }

        Optional<Student> checkExist2 = repository.findByName(student.getName());
        if(checkExist2.isPresent()){
            throw new BusinessException("Já existe um estudante com esse nome:"+student
                    .getName());
        }
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
    public Student delete(Long id) {
        Optional<Student> student = repository.findById(id);

        if(Boolean.FALSE.equals(student.isPresent())){
            throw new BusinessException("Student id: "+id+" não encontrado", 404);
        }

        try {
            repository.delete(student.get());
        }catch (DataIntegrityViolationException e){
            throw new BusinessException("Student id: "+id+" não pode ser removido," +
                    " por questões de integridade");
        }

        return student.get();
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
