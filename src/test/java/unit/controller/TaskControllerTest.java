package unit.controller;

import com.labdessoft.roteiro01.DTO.response.TaskDTO;
import com.labdessoft.roteiro01.controller.TaskController;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private TaskDTO taskDTO;
    private Task task;

    @BeforeEach
    void setUp() {
        taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setDescription("Test Task");
        taskDTO.setPriority(com.labdessoft.roteiro01.Enum.Priority.MEDIA);
        taskDTO.setDate(java.time.LocalDate.now());
        taskDTO.setDays(5);
        taskDTO.setType(com.labdessoft.roteiro01.Enum.Type.DATA);
        taskDTO.setCompleted(false);
        taskDTO.setStatus("PREVISTA");

        task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");
        task.setPriority(com.labdessoft.roteiro01.Enum.Priority.MEDIA);
        task.setDate(java.time.LocalDate.now());
        task.setDays(5);
        task.setType(com.labdessoft.roteiro01.Enum.Type.DATA);
        task.setCompleted(false);
    }

    @Test
    void testGetTask() {
        when(taskService.getTask(anyLong())).thenReturn(taskDTO);

        ResponseEntity<TaskDTO> response = taskController.getTask(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskDTO, response.getBody());
    }

    @Test
    void testGetTask_NotFound() {
        when(taskService.getTask(anyLong())).thenReturn(null);

        ResponseEntity<TaskDTO> response = taskController.getTask(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testListAll() {
        when(taskService.listAll()).thenReturn(List.of(task));

        ResponseEntity<List<Task>> response = taskController.listAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testListAll_NoContent() {
        when(taskService.listAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Task>> response = taskController.listAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testCreateTask() {
        when(taskService.create(any(TaskDTO.class))).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(taskDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testDoneTask() {
        when(taskService.setDone(anyLong())).thenReturn(task);

        ResponseEntity<Task> response = taskController.doneTask(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskService).delete(anyLong());

        ResponseEntity<Object> response = taskController.deleteTask(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateTask() {
        when(taskService.taskUpdate(any(Task.class), anyLong())).thenReturn(new ResponseEntity<>(task, HttpStatus.OK));

        ResponseEntity<Task> response = taskController.updateTask(1L, task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }
}

