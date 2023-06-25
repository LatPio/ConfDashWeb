package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.repository.AbstractRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttachmentFileUserControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private AbstractRepository abstractRepository;

    @Test
    @Order(1)
    void canUserSaveAttachmentFile() {
        abstractRepository.save(getSampleOfOneAbstractEntity());
        String body = "{\"fileRole\":\"OTHER\", \"abstractsEntity\":{\"id\":1}}";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .multiPart("data", body,"application/json")
                .when()
                .post("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("OTHER", response.jsonPath().getString("fileRole"));
    }

    @Test
    @Order(2)
    void canUSerGetAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "2")
                .when()
                .get("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test.txt", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void canUserDownloadAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/user/attachment_file/file/2")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void canUserGetListFiles() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/user/attachment_file/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void canUserUpdateAttachmentFile() {
        String body = "{\"id\":\"2\", \"fileRole\":\"FIGURE\", \"accepted\":\"false\", \"abstractsEntity\":{\"id\":1}}";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .multiPart("data", body,"application/json")
                .when()
                .put("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("FIGURE", response.jsonPath().getString("fileRole"));
    }

    @Test
    @Order(6)
    void canUserDeleteAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "2")
                .when()
                .delete("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void roleList() {
    }
}