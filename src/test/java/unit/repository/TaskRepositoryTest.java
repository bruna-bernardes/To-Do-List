package unit.repository;

import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setDescription("Test Task");
        task.setCompleted(false);
        task.setPriority(com.labdessoft.roteiro01.Enum.Priority.MEDIA);
        task.setDate(LocalDate.now());
        task.setDays(5);
        task.setType(com.labdessoft.roteiro01.Enum.Type.PRAZO);

        task = taskRepository.save(task);
    }

    @Test
    void testFindById() {
        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertTrue(foundTask.isPresent());
        assertEquals(task.getId(), foundTask.get().getId());
    }

    @Test
    void testFindAll() {
        List<Task> tasks = taskRepository.findAll();
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }

    @Test
    void testSave() {
        Task newTask = new Task();
        newTask.setDescription("New Test Task");
        newTask.setCompleted(false);
        newTask.setPriority(com.labdessoft.roteiro01.Enum.Priority.BAIXA);
        newTask.setDate(LocalDate.now());
        newTask.setDays(10);
        newTask.setType(com.labdessoft.roteiro01.Enum.Type.DATA);

        Task savedTask = taskRepository.save(newTask);
        assertNotNull(savedTask.getId());
        assertEquals("New Test Task", savedTask.getDescription());
    }

    @Test
    void testDelete() {
        taskRepository.delete(task);
        Optional<Task> deletedTask = taskRepository.findById(task.getId());
        assertFalse(deletedTask.isPresent());
    }

    @Test
    void testUpdate() {
        task.setDescription("Updated Test Task");
        Task updatedTask = taskRepository.save(task);
        assertEquals("Updated Test Task", updatedTask.getDescription());
    }
}

