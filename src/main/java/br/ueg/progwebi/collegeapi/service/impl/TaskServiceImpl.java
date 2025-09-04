package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.repository.TaskRepository;
import br.ueg.progwebi.collegeapi.service.CrudGenericService;
import br.ueg.progwebi.collegeapi.service.TaskService;
import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl
        extends CrudGenericServiceImpl<Task, Long, TaskRepository>
        implements TaskService {

    @Override
    protected Task prepareToCreate(Task student) {
        student.setId(null);
        student.setCompleted(false);
        return student;
    }

    @Override
    protected void createValidation(Task student) {
        if(student.getDescription()==null || student.getDescription().trim().equals("")){
            throw new BusinessException("Descrição da tarefa obrigatória");
        }
    }

    @Override
    protected Task prepareToUpdate(Task studentUpdate) {
        return null;
    }

    @Override
    protected void updateValidation(Task student) {
        this.createValidation(student);
    }


}
