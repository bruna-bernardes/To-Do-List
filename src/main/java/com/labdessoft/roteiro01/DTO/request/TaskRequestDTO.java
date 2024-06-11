package com.labdessoft.roteiro01.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TaskRequestDTO {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("Concluida")
    private boolean completed;

    @JsonProperty("data")
    private LocalDate date;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("tipo_tarefa")
    private Type type;

    @JsonProperty("prioridade")
    private Priority priority;

    @JsonProperty("status")
    private String status;

}
