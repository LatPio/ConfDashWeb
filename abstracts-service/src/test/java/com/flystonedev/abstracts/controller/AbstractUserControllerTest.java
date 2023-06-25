package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.repository.AbstractRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AbstractUserControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private AbstractRepository abstractRepository;
    @BeforeEach
    void setUp() {

        abstractRepository.saveAll(getSampleAbstract());

    }
    Integer startId = null;
    @Test
    @Order(1)
    void addAbstract() {
        String requestBody = "{\n" +
                "  \"abstractTitle\": \"Abstract Title\",\n" +
                "  \"body\": \"Body of Abstract\",\n" +
                "  \"authId\": \"authorization\",\n" +
                "  \"presenterId\": \"1\" \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .header("Authorization", getTokenAdminByWebclient())
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Abstract Title", response.jsonPath().getString("abstractTitle"));
        startId = response.jsonPath().getInt("id");
    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "12")
                .when()
                .get("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Abstract Title", response.jsonPath().getString("abstractTitle"));

    }

    @Test
    @Order(3)
    void list() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/user/abstracts/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void listAccepted() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/user/abstracts/listAccepted")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void update() {
        String requestBody = "{\n" +
                "  \"id\": \"12\",\n" +
                "  \"abstractTitle\": \"Abstract New Title\",\n" +
                "  \"body\": \"Body of Abstract\" \n" +
                " \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Abstract New Title", response.jsonPath().getString("abstractTitle"));

    }

    @Test
    @Order(6)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "12")
                .when()
                .delete("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}