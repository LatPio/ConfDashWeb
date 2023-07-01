package com.flystonedev.customer.controller;

import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.config.KeycloakTestContainers;
import com.flystonedev.customer.model.Institution;
import com.flystonedev.customer.repository.InstitutionRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private InstitutionRepository institutionRepository;

    @BeforeEach
    void setUp() {

        institutionRepository.save(Institution.builder().id(1).name("uj").build());

    }
    @Test
    @Order(1)
    void addDepartment() {
        String requestBody = "{\n" +
                "  \"name\": \"Some Department\",\n" +
                "  \"street\": \"first Street\",\n" +
                "  \"buildingNumber\": \"12B\",\n" +
                "  \"flatNumber\": \"f1-23\",\n" +
                "  \"city\": \"NY\",\n" +
                "  \"postalCode\": \"12345-1\",\n" +
                "  \"institution\": {\"id\": \"1\" },\n" +
                "  \"country\": \"USA\" \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/department")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
//        Assertions.assertEquals("Abstract Title", response.jsonPath().getString("abstractTitle"));
    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/department")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Some Department", response.jsonPath().getString("name"));

    }

    @Test
    @Order(3)
    void list() {

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/department/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void update() {
        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"name\": \"Some new Department\",\n" +
                "  \"street\": \"first Street\",\n" +
                "  \"buildingNumber\": \"12B\",\n" +
                "  \"flatNumber\": \"f1-23\",\n" +
                "  \"city\": \"NY\",\n" +
                "  \"postalCode\": \"12345-1\",\n" +
                "  \"institution\": {\"id\": \"1\" },\n" +
                "  \"country\": \"USA\" \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/department")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Some new Department", response.jsonPath().getString("name"));

    }

    @Test
    @Order(5)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/department")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}