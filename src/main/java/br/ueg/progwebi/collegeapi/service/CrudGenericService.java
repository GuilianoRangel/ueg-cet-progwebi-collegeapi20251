package br.ueg.progwebi.collegeapi.service;

import br.ueg.progwebi.collegeapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudGenericService<MODEL, PKTYPE, REPOSITORY extends JpaRepository<MODEL, PKTYPE>> {
    List<MODEL> listAll();
    MODEL getbyId(PKTYPE id);
    MODEL create(MODEL student);
    MODEL update(PKTYPE id, MODEL student);
    MODEL delete(PKTYPE id);
}
