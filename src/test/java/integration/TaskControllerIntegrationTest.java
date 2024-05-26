package integration;

import com.labdessoft.roteiro01.Roteiro01Application;
import com.labdessoft.roteiro01.DTO.response.TaskDTO;
import com.labdessoft.roteiro01.entity.Task;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static mock.MockFactory.tarefaRequestDTOMockFactory;
import static mock.MockFactory.tarefaEntityMockFactory;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Roteiro01Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TaskControllerIntegrationTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
    }

    @Test
    public void testListAll() {
        given()
                .when()
                .get("/task")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testGetTask() {
        given()
                .when()
                .get("/task/{taskId}", 1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1));
    }

    @Test
    public void testCreateTask() {
        TaskDTO taskDTO = tarefaRequestDTOMockFactory();

        given()
                .contentType(ContentType.JSON)
                .body(taskDTO)
                .when()
                .post("/task")
                .then()
                .statusCode(201)
                .body("description", equalTo("Test Description"))
                .body("priority", equalTo("MEDIA"));
    }

    @Test
    public void testDeleteTask() {
        given()
                .when()
                .delete("/task/{id}", 1)
                .then()
                .statusCode(204);
    }

    @Test
    public void testUpdateTask() {
        Task task = tarefaEntityMockFactory();
        task.setDescription("Updated Task Description");

        given()
                .contentType(ContentType.JSON)
                .body(task)
                .when()
                .put("/task/{id}/update", 1)
                .then()
                .statusCode(200)
                .body("description", equalTo("Updated Task Description"))
                .body("priority", equalTo("ALTA"));
    }

    @Test
    public void testDoneTask() {
        given()
                .when()
                .put("/task/{id}/done", 1)
                .then()
                .statusCode(200)
                .body("completed", equalTo(true));
    }
}
