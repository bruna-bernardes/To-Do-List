package unit.service;

import com.labdessoft.roteiro01.DTO.response.TaskDTO;
import com.labdessoft.roteiro01.Enum.Priority;
import com.labdessoft.roteiro01.Enum.Type;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.repository.TaskRepository;
import com.labdessoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private TaskDTO taskDTO;
    private Task task;

    @BeforeEach
    void setUp() {
        taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setDescription("Test Task");
        taskDTO.setPriority(Priority.MEDIA);
        taskDTO.setDate(LocalDate.now());
        taskDTO.setDays(5);
        taskDTO.setType(Type.DATA);
        taskDTO.setCompleted(false);
        taskDTO.setStatus("PREVISTA");

        task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");
        task.setPriority(Priority.MEDIA);
        task.setDate(LocalDate.now());
        task.setDays(5);
        task.setType(Type.DATA);
        task.setCompleted(false);
    }

    @Test
    void testGetTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        TaskDTO result = taskService.getTask(1L);

        assertEquals(taskDTO.getId(), result.getId());
        assertEquals(taskDTO.getDescription(), result.getDescription());
        assertEquals(taskDTO.getPriority(), result.getPriority());
        assertEquals(taskDTO.getDate(), result.getDate());
        assertEquals(taskDTO.getDays(), result.getDays());
        assertEquals(taskDTO.getType(), result.getType());
        assertEquals(taskDTO.getCompleted(), result.getCompleted());
    }

    @Test
    void testGetTask_NotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        TaskDTO result = taskService.getTask(1L);

        assertEquals(null, result);
    }

    @Test
    void testCreateTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.create(taskDTO);

        assertEquals(task.getId(), result.getId());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getPriority(), result.getPriority());
        assertEquals(task.getDate(), result.getDate());
        assertEquals(task.getDays(), result.getDays());
        assertEquals(task.getType(), result.getType());
        assertEquals(task.getCompleted(), result.getCompleted());
    }

    @Test
    void testListAll() {
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> result = taskService.listAll();

        assertEquals(1, result.size());
        assertEquals(task, result.get(0));
    }

    @Test
    void testListAll_Empty() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        List<Task> result = taskService.listAll();

        assertEquals(0, result.size());
    }

    @Test
    void testSetDone() {
        when(taskRepository.getReferenceById(anyLong())).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.setDone(1L);

        assertEquals(true, result.getCompleted());
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskRepository).deleteById(anyLong());

        taskService.delete(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = new Task();
        updatedTask.setDescription("Updated Task");
        updatedTask.setDays(10);
        updatedTask.setType(Type.PRAZO);
        updatedTask.setPriority(Priority.ALTA);
        updatedTask.setDate(LocalDate.now().plusDays(5));

        ResponseEntity<Task> response = taskService.taskUpdate(updatedTask, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTask.getDescription(), response.getBody().getDescription());
        assertEquals(updatedTask.getDays(), response.getBody().getDays());
        assertEquals(updatedTask.getType(), response.getBody().getType());
        assertEquals(updatedTask.getPriority(), response.getBody().getPriority());
        assertEquals(updatedTask.getDate(), response.getBody().getDate());
    }
}
