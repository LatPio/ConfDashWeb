package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.clients.CustomerClient;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.repository.AbstractRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AttachmentFileUserControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private AbstractRepository abstractRepository;
    @MockBean
    private CustomerClient customerClient;

    @Test
    @Order(1)
    void canUserSaveAttachmentFile() {
        abstractRepository.save(getSampleOfOneAbstractEntity());
        String body = "{\"fileRole\":\"GRAPHICAL_ABSTRACT\", \"abstractsEntity\":{\"id\":1}}";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/kot.jpg"),"image/jpeg")
                .multiPart("data", body,"application/json")
                .when()
                .post("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("GRAPHICAL_ABSTRACT", response.jsonPath().getString("fileRole"));
        Assertions.assertEquals("kot.jpg", response.jsonPath().getString("name"));
        Assertions.assertEquals("image/jpeg", response.jsonPath().getString("type"));
    }

    @Test
    @Order(2)
    void canUSerGetAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("GRAPHICAL_ABSTRACT", response.jsonPath().getString("fileRole"));
        Assertions.assertEquals("kot.jpg", response.jsonPath().getString("name"));
        Assertions.assertEquals("image/jpeg", response.jsonPath().getString("type"));
    }

    @Test
    @Order(3)
    void canUserDownloadAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/user/attachment_file/file/1")
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
        String body = "{\"id\":\"1\", \"fileRole\":\"FILE_ABSTRACT\", \"accepted\":\"false\", \"abstractsEntity\":{\"id\":1}}";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/kot.jpg"),"image/jpeg")
                .multiPart("data", body,"application/json")
                .when()
                .put("api/v1/user/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("FILE_ABSTRACT", response.jsonPath().getString("fileRole"));
        Assertions.assertEquals("kot.jpg", response.jsonPath().getString("name"));
        Assertions.assertEquals("image/jpeg", response.jsonPath().getString("type"));
    }

    @Test
    @Order(6)
    void canUserDeleteAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
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