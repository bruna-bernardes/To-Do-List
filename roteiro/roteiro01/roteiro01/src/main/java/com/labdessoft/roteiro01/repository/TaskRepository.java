package main.java.com.labdessoft.roteiro01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import main.java.com.labdessoft.roteiro01.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}