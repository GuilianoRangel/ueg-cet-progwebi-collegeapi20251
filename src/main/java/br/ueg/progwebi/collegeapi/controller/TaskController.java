package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.dto.StudentDataDTO;
import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> listAll(){
        return taskService.listAll();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        return ResponseEntity.ok(taskService.create(task));
    }
    @PutMapping(path = "/{id}")
    public Task update(
            @PathVariable Long id,
            @RequestBody Task task){
        return taskService.update(id, task);
    }

    @GetMapping(path = "/{id}")
    public Task getById(@PathVariable Long id){

        return this.taskService.getbyId(id);
    }

    @DeleteMapping(path = "/{id}")
    public Task delete(@PathVariable Long id){
        return this.taskService.delete(id);
    }
}
