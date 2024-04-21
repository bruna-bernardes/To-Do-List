package com.labdessoft.roteiro01.DTO;

import java.time.LocalDate;

import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import com.labdessoft.roteiro01.entity.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TaskDTO {
    @NotBlank
    private Long id;
    private String description;
    private Boolean completed;
    private Priority priority;
    private LocalDate date;
    private Integer days; //tarefas do tipo prazo
    private Type type;

    public void setStatus(String s) {
    }
}