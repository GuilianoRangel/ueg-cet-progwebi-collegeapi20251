package br.ueg.progwebi.collegeapi.service;

import br.ueg.progwebi.collegeapi.model.Student;
import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.repository.StudentRepository;
import br.ueg.progwebi.collegeapi.repository.TaskRepository;

import java.util.List;

public interface TaskService
 extends CrudGenericService<Task, Long, TaskRepository> {
    //List<Task> listTaskCompleted(Boolean completed);
}
