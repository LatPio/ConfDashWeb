package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.clients.CustomerClient;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.repository.AbstractRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;

@Testcontainers
public class AbstractAdminControllerTest extends KeycloakTestContainers implements SampleData {

    @MockBean
    private CustomerClient customerClient;

    @Autowired
    private AbstractRepository abstractRepository;
    @BeforeEach
     void setUp() {
        abstractRepository.saveAll(getSampleAbstract());
    }

    public AbstractAdminControllerTest() {
    }

    @Test
    void addAbstract() {
        String requestBody = "{\n" +
                "  \"abstractTitle\": \"Abstract Title\",\n" +
                "  \"body\": \"Body of Abstract\",\n" +
                "  \"authId\": \"authorization\",\n" +
                "  \"presenterId\": \"1\" \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/admin/abstracts")
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Abstract Title", response.jsonPath().getString("abstractTitle"));

    }

    @Test
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/admin/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Title: Sample Abstract", response.jsonPath().getString("abstractTitle"));

    }


    @Test
    void getSimple() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/admin/abstracts/simple")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Title: Sample Abstract", response.jsonPath().getString("abstractTitle"));

    }

    @Test
    void list() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/admin/abstracts/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void updateAdmin() {

        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"abstractTitle\": \"Abstract New Title\",\n" +
                "  \"body\": \"Body of Abstract\",\n" +
                "  \"authors\": \"authors\",\n" +
                "  \"affiliation\": \"affiliation\",\n" +
                "  \"ownerId\": \"1\",\n" +
                "  \"authId\": \"authorization\",\n" +
                "  \"accepted\": \"false\",\n" +
                "  \"files\": [],\n" +
                "  \"comments\": \"comments\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/admin/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Abstract New Title", response.jsonPath().getString("abstractTitle"));
    }

    @Test
    void updateBlockEdit() {

        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"accepted\": \"true\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/admin/abstracts/block")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("true", response.jsonPath().getString("accepted"));

    }

    @Test
    void delete() {

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "3")
                .when()
                .delete("api/v1/admin/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }


}