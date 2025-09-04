package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.repository.TaskRepository;
import br.ueg.progwebi.collegeapi.service.TaskService;
import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Task> listAll() {
        return repository.findAll();
    }

    @Override
    public Task getbyId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Task create(Task task) {
        if(task.getDescription()==null || task.getDescription().trim().equals("")){
            throw new BusinessException("Descrição da tarefa obrigatória");
        }
        task.setId(null);
        task.setCompleted(false);
        return repository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {
        Task taskDB = repository.findById(id).orElse(null);
        if(taskDB!=null){
            taskDB.setDescription(task.getDescription());
            taskDB.setCompleted(task.getCompleted());
            return repository.save(taskDB);
        }
        return null;
    }

    @Override
    public Task delete(Long id) {
        Task task = repository.findById(id).orElse(null);
        if(task != null){
            this.repository.delete(task);
        }else{
            throw new BusinessException("Tarefa não existe");
        }
        return task;
    }
}
