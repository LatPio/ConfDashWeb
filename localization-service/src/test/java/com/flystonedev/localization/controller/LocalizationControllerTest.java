package com.flystonedev.localization.controller;

import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocalizationControllerTest extends KeycloakTestContainers implements SampleData {

    @Test
    @Order(1)
    void addLocalization() {
        String requestBody = "{\n" +
                "  \"room\": \"Room 1\",\n" +
                "  \"coordinateX\": 1,\n" +
                "  \"coordinateY\": 1,\n" +
                "  \"mapImage\": null \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/localization")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/localization")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(3)
    void getSimple() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/localization")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void listResponseEntity() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/localization/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void update() {
        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"room\": \"Room 1\",\n" +
                "  \"coordinateX\": 1,\n" +
                "  \"coordinateY\": 1,\n" +
                "  \"mapImage\": null \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/localization")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(6)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/localization")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}