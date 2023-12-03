package com.flystonedev.site.controller;

import com.flystonedev.site.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static io.restassured.RestAssured.given;
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PhotoAndFilesControllerTest extends KeycloakTestContainers {

    @Test
    @Order(1)
    void saveFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .when()
                .post("api/v1/files")
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
                .get("api/v1/files")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test.txt", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void download() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/files/download/1")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void list() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/files/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void getDownloadList() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/files/downloadList")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(6)
    void update() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .param("id", "1")
                .when()
                .put("api/v1/files")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(7)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/files")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }
}