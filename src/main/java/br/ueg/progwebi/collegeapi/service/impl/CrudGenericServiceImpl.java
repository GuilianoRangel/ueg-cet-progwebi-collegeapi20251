package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.repository.StudentRepository;
import br.ueg.progwebi.collegeapi.service.CrudGenericService;
import br.ueg.progwebi.collegeapi.service.StudentService;
import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public abstract class CrudGenericServiceImpl<MODEL, PKTYPE, REPOSITORY extends JpaRepository<MODEL, PKTYPE>>
        implements CrudGenericService<MODEL, PKTYPE, REPOSITORY > {

    @Autowired
    protected REPOSITORY repository;

    @Override
    public List<MODEL> listAll() {
        return repository.findAll();
    }

    @Override
    public MODEL getbyId(PKTYPE id) {
        Optional<MODEL> student = this.repository.findById(id);

        if(Boolean.FALSE.equals(student.isPresent())){
            throw new BusinessException("Dado para o id: "+id+" não encontrado", 404);
        }else{
            return student.get();
        }
    }

    @Override
    public MODEL create(MODEL student) {
        createValidation(student);
        prepareToCreate(student);
        return repository.save(student);

    }

    protected abstract MODEL prepareToCreate(MODEL student) ;

    protected abstract void createValidation(MODEL student) ;

    @Override
    public MODEL update(PKTYPE id, MODEL studentUpdate) {
        MODEL dbStudent = this.getbyId(id);
        updateValidation(studentUpdate);
        prepareToUpdate(studentUpdate);
        return repository.save(dbStudent);
    }

    protected abstract MODEL prepareToUpdate(MODEL studentUpdate);

    protected abstract void updateValidation(MODEL student) ;

    @Override
    public MODEL delete(PKTYPE id) {
        MODEL student = this.getbyId(id);

        try {
            repository.delete(student);
        }catch (DataIntegrityViolationException e){
            throw new BusinessException("Entidade do id: "+id+" não pode ser removido," +
                    " por questões de integridade");
        }

        return student;
    }
}
