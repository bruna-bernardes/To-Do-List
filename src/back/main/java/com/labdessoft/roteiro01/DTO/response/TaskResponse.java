package com.labdessoft.roteiro01.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)

public class TaskResponse {
    @JsonProperty("tarefas")
    private List<TaskDTO> tarefas;
}
