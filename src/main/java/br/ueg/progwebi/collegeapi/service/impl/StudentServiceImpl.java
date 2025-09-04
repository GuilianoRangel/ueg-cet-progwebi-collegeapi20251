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
public class StudentServiceImpl
        extends CrudGenericServiceImpl<Student, Long, StudentRepository>
        implements StudentService {

    private StudentRepository rep = null;

    private StudentRepository getLocalRepository(){
        if(this.rep == null){
            this.rep = (StudentRepository) this.repository;
        }
        return this.rep;
    }

    protected Student prepareToCreate(Student student) {
        student.setRegisterDate(LocalDate.now());
        student.setRegisterNumber(getNewRegisterNumberStudent());
        return student;
    }

    private String getNewRegisterNumberStudent() {

        Optional<Student> topByOrderByIdDesc = this.getLocalRepository().findTopByOrderByIdDesc();
        Student topStudent = topByOrderByIdDesc.orElse(Student.builder().id(0L).build());
        Long newId = topStudent.getId()+1;
        Integer year = LocalDate.now().getYear();
        return year.toString().concat(newId.toString());
    }

    protected void createValidation(Student student) {
        if(Strings.isEmpty(student.getName())){
            throw new BusinessException("Name não pode ser nulo ou vazio");
        }

        Optional<Student> checkExist2 = this.getLocalRepository().findByName(student.getName());
        if(checkExist2.isPresent()){
            throw new BusinessException("Já existe um estudante com esse nome:"+student
                    .getName());
        }
    }

    @Override
    protected Student prepareToUpdate(Student studentUpdate) {
        studentUpdate.setName(studentUpdate.getName());
        studentUpdate.setCourse(studentUpdate.getCourse());
        return  studentUpdate;
    }


    protected void updateValidation(Student student) {
        if(Strings.isEmpty(student.getName()) ||
                Objects.isNull(student.getId()) ||
                student.getId().longValue()==0
        ){
            throw new BusinessException("Information incomplete (name or ID)");
        }
    }


    @Override
    public List<Student> listStudentsCourse(String course) {
        Optional<List<Student>> allStudentsCourse = this.getLocalRepository().findAllStudentsCourse(course);
        return allStudentsCourse.orElseGet(List::of);
        /* o returno acima é equivalente ao código abaixo
        if(allStudentsCourse.isPresent()){
            return allStudentsCourse.get();
        }
        return List.of();*/
    }
}
