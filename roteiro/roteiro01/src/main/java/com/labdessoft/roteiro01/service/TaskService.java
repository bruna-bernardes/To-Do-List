package com.labdessoft.roteiro01.service;

import com.labdessoft.roteiro01.DTO.TaskDTO;
import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDTO getTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return null;
        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setType(task.getType());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setDate(task.getDate());
        taskDTO.setDays(task.getDays());

        // Definindo o status da tarefa com base no tipo
        if (task.getType() == Type.DATA) {
            LocalDate currentDate = LocalDate.now();
            if (task.getDate().isBefore(currentDate)) {
                long daysLate = ChronoUnit.DAYS.between(task.getDate(), currentDate);
                taskDTO.setStatus("Atrasada por " + daysLate + " dias");
            } else {
                taskDTO.setStatus("Prevista");
            }
        } else if (task.getType() == Type.PRAZO) {
            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = task.getDate().plusDays(task.getDays());
            if (dueDate.isBefore(currentDate)) {
                long daysLate = ChronoUnit.DAYS.between(dueDate, currentDate);
                taskDTO.setStatus("Atrasada por " + daysLate + " dias");
            } else {
                taskDTO.setStatus("Prevista");
            }
        } else {
            taskDTO.setStatus("Prevista");
        }

        return taskDTO;
    }

    public Task create(TaskDTO task) {
        Task entity = new Task();

        if (task.getDate() != null) {
            // SETAR O TIPO, SETAR A DATA
            entity.setDate(task.getDate());// preenchendo a entidade com o que o usuário mandou
            entity.setType(Type.DATA);
        } else if (task.getDays() != null) {
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataFinal = dataAtual.plusDays(task.getDays());
            // Definir a data de conclusão para tarefas do tipo PRAZO
            entity.setDate(dataFinal);
            entity.setType(Type.PRAZO);
        } else {
            entity.setType(Type.LIVRE);
            // Livre default
        }

        // Preencher a descrição
        entity.setDescription(task.getDescription());

        // Definir completed como falso (pois é uma nova tarefa e não foi completada ainda)
        entity.setCompleted(false);

        if (task.getPriority() == null) {
            entity.setPriority(Priority.BAIXA);
        } else {
            entity.setPriority(task.getPriority());
        }
        return taskRepository.save(entity);
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
