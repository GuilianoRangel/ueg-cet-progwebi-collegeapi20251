package br.ueg.progwebi.collegeapi.service.impl;

import br.ueg.progwebi.collegeapi.model.Task;
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
    private TaskRepository taskRepository;

    @Override
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getbyId(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task create(Task task) {
        if(task.getDescription()==null || task.getDescription().trim().equals("")){
            throw new BusinessException("Descrição da tarefa obrigatória");
        }
        task.setId(null);
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {
        Task taskDB = taskRepository.findById(id).orElse(null);
        if(taskDB!=null){
            taskDB.setDescription(task.getDescription());
            taskDB.setCompleted(task.getCompleted());
            return taskRepository.save(taskDB);
        }
        return null;
    }

    @Override
    public Task delete(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if(task != null){
            this.taskRepository.delete(task);
        }else{
            throw new BusinessException("Tarefa não existe");
        }
        return task;
    }
}
