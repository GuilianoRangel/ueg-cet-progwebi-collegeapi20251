package br.ueg.progwebi.collegeapi.service;

import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> listAll();
    Task getbyId(Long id);
    Task create(Task task);
    Task update(Long id, Task task);
    Task delete(Long id);
    //List<Task> listTaskCompleted(Boolean completed);
}
