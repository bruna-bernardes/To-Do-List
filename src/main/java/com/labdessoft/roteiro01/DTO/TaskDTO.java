package com.labdessoft.roteiro01.DTO;

import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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