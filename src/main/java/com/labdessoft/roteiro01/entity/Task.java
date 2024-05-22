package com.labdessoft.roteiro01.entity;

import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Todos os detalhes sobre uma tarefa. ")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean completed;
    private Priority priority;
    private LocalDate date;
    private Integer days; //tarefas do tipo prazo
    private Type type;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" +
                completed + "]";
    }

}