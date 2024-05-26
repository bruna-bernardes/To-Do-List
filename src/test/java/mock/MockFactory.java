package mock;

import com.labdessoft.roteiro01.DTO.response.TaskDTO;
import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import com.labdessoft.roteiro01.entity.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockFactory {

    public static TaskDTO tarefaRequestDTOMockFactory() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setDescription("Test Description");
        taskDTO.setDate(LocalDate.now());
        taskDTO.setDays(5); // Ajustar o valor de dias conforme necessário
        taskDTO.setPriority(Priority.MEDIA);
        taskDTO.setType(Type.DATA);
        taskDTO.setStatus("PREVISTA");
        taskDTO.setCompleted(false);
        return taskDTO;
    }

    public static Task tarefaEntityMockFactory() {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Test Description");
        task.setCompleted(false);
        task.setPriority(Priority.ALTA);
        task.setDate(LocalDate.now());
        task.setDays(5); // Ajustar o valor de dias conforme necessário
        task.setType(Type.DATA);
        return task;
    }
}
