package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.repository.AbstractRepository;
import com.flystonedev.abstracts.repository.AttachmentFileRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttachmentFileAdminControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private AbstractRepository abstractRepository;

    @Test
    @Order(1)
    void canAdminSaveAttachmentFile() {
        abstractRepository.save(getSampleOfOneAbstractEntity());
        String body = "{\"fileRole\":\"OTHER\",\"accepted\":\"false\", \"abstractsEntity\":{\"id\":1}}";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .multiPart("data", body,"application/json")
                .when()
                .post("api/v1/admin/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("OTHER", response.jsonPath().getString("fileRole"));

    }

    @Test
    @Order(2)
    void canAdminGetAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/admin/attachment_file")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test.txt", response.jsonPath().getString("name"));

    }

    @Test
    @Order(3)
    void canAdminDownloadAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/admin/attachment_file/file/1")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    @Order(4)
    void canAdminGetListOfAttachmentFiles() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/admin/attachment_file/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void canAdminUpdateAttachmentFile() {
        String body = "{\"id\":\"1\", \"fileRole\":\"FIGURE\", \"accepted\":\"false\", \"abstractsEntity\":{\"id\":1} }";
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .multiPart("data", body,"application/json")
                .when()
                .put("api/v1/admin/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("FIGURE", response.jsonPath().getString("fileRole"));
    }

    @Test
    @Order(6)
    void canAdminDeleteAttachmentFile() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/admin/attachment_file")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void roleList() {
    }
}