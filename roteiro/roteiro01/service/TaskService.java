package com.labdessoft.roteiro01.service;

import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    public Task setDone(Long id) {
        Task task = taskRepository.getReferenceById(id);
        if (task.getCompleted()) return task;
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    public ResponseEntity<Task> taskUpdate(Task task, long id) {
        return taskRepository.findById(id)
                .map(updateTask -> {
                    updateTask.setDescription(task.getDescription());
                    updateTask.setCompleted(false);
                    Task updated = taskRepository.save(updateTask);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
