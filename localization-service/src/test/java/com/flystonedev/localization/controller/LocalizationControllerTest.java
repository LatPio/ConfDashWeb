package com.flystonedev.localization.controller;

import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.config.KeycloakTestContainers;
import com.flystonedev.localization.repository.MapImageRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class LocalizationControllerTest extends KeycloakTestContainers implements SampleData {




    @Autowired
    private MapImageRepository mapImageRepository;

    @BeforeEach
    void setUp() {
        mapImageRepository.save(getSampleOfMapImageRepositoryAdd());

    }
    @Test
    @Order(1)
    void addLocalization() {
        String requestBody = """
                {
                  "room": "Room 1",
                  "coordinateX": 1,
                  "coordinateY": 1,
                  "mapImage": {
                    "id": 1}
                }""";
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
        String requestBody = """
                {
                  "id": "1",
                  "room": "Room 1",
                  "coordinateX": 1,
                  "coordinateY": 1,
                  "mapImage": null
                }""";
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