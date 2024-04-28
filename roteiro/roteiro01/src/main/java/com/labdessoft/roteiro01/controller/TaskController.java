package com.labdessoft.roteiro01.controller;

import com.labdessoft.roteiro01.DTO.TaskDTO;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @ApiOperation(value = "Retorna uma tarefa expecífica")
    @GetMapping("/{taskId}")
    @Operation(summary = "Retorna uma tarefa específica através no ID")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        TaskDTO taskDTO = taskService.getTask(taskId);
        if (taskDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDTO);
    }


    @GetMapping()
    @Operation(summary = "Lista todas as tarefas criadas")
    public ResponseEntity<List<Task>> listAll() {
        try {
            List<Task> taskList = taskService.listAll();
            if (taskList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Criar uma nova tarefa")
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDTO task) {
        try {
            Task _task = taskService.create(task);
            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{id}/done")
    @Operation(summary = "Concluir uma tarefa já criada")
    public ResponseEntity<Task> doneTask(@PathVariable Long id) {
        try {
            Task result = taskService.setDone(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Remover uma tarefa já criada")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        try {
            taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "{id}/update")
    @Operation(summary = "Alterar a descrição de uma tarefa já criada")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.taskUpdate(task, id);
    }
}