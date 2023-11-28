package com.flystonedev.localization.controller;

import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static io.restassured.RestAssured.given;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class MapImageControllerTest extends KeycloakTestContainers implements SampleData {

    @Test
    @Order(1)
    void saveFile() {
        String body = """
                {
                  "name": "map"
                }""";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .multiPart("data", body,"application/json")
                .and()
                .when()
                .post("api/v1/mapImage")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("map", response.jsonPath().getString("name"));

    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/mapImage")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("map", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void getWithRooms() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/mapImage/rooms")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    @Order(4)
    void getList() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/mapImage/list")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void getSimpleList() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/mapImage/simple-list")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(6)
    void update() {
        String body = """
                {
                  "id": "1",
                  "name": "map new"
                }""";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .multiPart("data", body,"application/json")
                .and()
                .when()
                .put("api/v1/mapImage")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("map new", response.jsonPath().getString("name"));
    }

    @Test
    @Order(7)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/mapImage")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }
}