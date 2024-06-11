package com.labdessoft.roteiro01.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labdessoft.roteiro01.DTO.request.TaskRequestDTO;  // Importe a classe TaskRequestDTO
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TaskResponse {
    @JsonProperty("tarefas")
    private List<TaskRequestDTO> tarefas;
}
