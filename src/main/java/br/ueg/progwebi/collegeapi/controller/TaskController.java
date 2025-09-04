package br.ueg.progwebi.collegeapi.controller;

import br.ueg.progwebi.collegeapi.model.Task;
import br.ueg.progwebi.collegeapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(
            produces = {"application/json"}
    )
    @Operation(
            description = "Método utilizado para realizar a listar de um entidade",
            responses = {
                    @ApiResponse(
                    responseCode = "200",
                    description = "Lista de entdiades",
                    useReturnTypeSchema = true),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Não existe entidades",
                            useReturnTypeSchema = true
            )}
    )
    @CrossOrigin
    public ResponseEntity<List<Task>> listAll(){
        List<Task> tasks = taskService.listAll();
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
    }

    @PostMapping(
            produces = {"application/json"})
    @Operation(
            description = "Método utilizado para realizar a inclusão de um entidade",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entidade Incluida",
                    useReturnTypeSchema = true
            )}
    )
    public ResponseEntity<Task> create(@RequestBody Task task){
        return ResponseEntity.ok(taskService.create(task));
    }
    @PutMapping(path = "/{id}",
            produces = {"application/json"})
    @Operation(
            description = "Método utilizado para realizar a atualizar de um entidade",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entidade atualizada",
                    useReturnTypeSchema = true
            )}
    )
    public ResponseEntity<Task> update(
            @PathVariable Long id,
            @RequestBody Task task){
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @GetMapping(path = "/{idd}",
            produces = {"application/json"}
    )
    @Operation(
            description = "Método utilizado para obter dados de um entidade",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entidade",
                    useReturnTypeSchema = true
            )}
    )
    public ResponseEntity<Task> getById(@PathVariable Long idd){
        return ResponseEntity.ok(this.taskService.getbyId(idd));
    }

    @DeleteMapping(path = "/{id}",
            produces = {"application/json"})
    @Operation(
            description = "Método utilizado para realizar a remoção de um entidade",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entidade removida",
                    useReturnTypeSchema = true
            )}
    )
    public ResponseEntity<Task> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.taskService.delete(id));
    }
}
