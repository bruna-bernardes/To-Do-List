package com.labdessoft.roteiro01.DTO.response;

import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class TaskDTO {
    @NotNull
    private Long id;
    private String description;
    private Boolean completed;
    private Priority priority;
    private LocalDate date;
    private Integer days; // Tarefas do tipo prazo
    private Type type;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
}
