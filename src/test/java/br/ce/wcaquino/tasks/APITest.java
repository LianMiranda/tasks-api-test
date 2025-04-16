package br.ce.wcaquino.tasks;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;

public class APITest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/tasks-backend";
    }

    @Test
    public void test() {
        RestAssured.given()
                .when()
                .get("/todo")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComSucesso() {
        RestAssured.given()
                .body("{\"task\": \"Descrição da tarefa\", \"dueDate\": \"2025-04-16\"}")
                .contentType("application/json")
                .when()
                .post("/todo")
                .then()
                .statusCode(201);
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida() {
        RestAssured.given()
                .body("{\"task\": \"\", \"dueDate\": \"2025-04-16\"}")
                .contentType("application/json")
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Fill the task description"));
    }
    
}

