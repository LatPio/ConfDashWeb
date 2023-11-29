package com.flystonedev.localization.controller;

import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.config.KeycloakTestContainers;
import com.flystonedev.localization.repository.LocalizationRepository;
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

class BookingControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private LocalizationRepository localizationRepository;

    @Autowired
    private MapImageRepository mapImageRepository;

    @BeforeEach
    void setUp() {
        mapImageRepository.save(getSampleOfMapImageRepositoryAdd());
        localizationRepository.save(getSampleOfLocalization());

    }

    @Test
    @Order(1)
    void createBooking() {
        String requestBody = """
                {
                  "eventIDData": 1,
                  "dateStart": "2023-06-08T12:15:22",
                  "eventTime": 1200,
                  "localization": {"id": 1 },
                  "locationConflict": false,
                  "timeConflict": false
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/booking")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    @Order(2)

    void bookingsDTOList() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/booking/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(3)

    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/booking")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
//        Assertions.assertEquals("Title: Sample Abstract", response.jsonPath().getString("abstractTitle"));
    }

    @Test
    @Order(4)

    void update() {
        String requestBody = """
                {
                  "id": "1",
                  "eventIDData": 1,
                  "dateStart": "2023-06-08T12:15:22",
                  "eventTime": 1200,
                  "localization": {"id": 1 },
                  "locationConflict": false,
                  "timeConflict": false
                  }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/booking")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)

    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/booking")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}