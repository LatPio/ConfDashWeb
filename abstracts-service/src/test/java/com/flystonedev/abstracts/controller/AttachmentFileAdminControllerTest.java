package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.repository.AbstractRepository;
import com.flystonedev.abstracts.repository.AttachmentFileRepository;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttachmentFileAdminControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private AttachmentFileRepository attachmentFileRepository;

    @Autowired
    private AbstractRepository abstractRepository;

    @BeforeEach
    void setUp() {

        abstractRepository.save(getSampleOfOneAbstractEntity());

    }
    @Test
    @Order(1)
    void saveFile() {

        String body = "{\"filerole\":\"OTHER\", \"abstractsEntity\":{\"id\":1}}";
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

    }

    @Test
    void get() {
    }

    @Test
    void download() {
    }

    @Test
    void getListFiles() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void roleList() {
    }
}