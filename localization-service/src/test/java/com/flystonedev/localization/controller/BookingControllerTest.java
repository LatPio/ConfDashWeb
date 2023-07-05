package com.flystonedev.localization.controller;

import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.config.KeycloakTestContainers;
import com.flystonedev.localization.repository.BookingsRepository;
import com.flystonedev.localization.repository.LocalizationRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private LocalizationRepository localizationRepository;

    @BeforeEach
    void setUp() {

        localizationRepository.save(getSampleOfLocalization());

    }

    @Test
    @Order(1)
    void createBooking() {
        String requestBody = "{\n" +
                "  \"eventIDData\": 1,\n" +
                "  \"dateStart\": \"2023-06-08T12:15:22\",\n" +
                "  \"eventTime\": 1200,\n" +
                "  \"localization\": {\"id\": 1 },\n" +
                "  \"locationConflict\": false,\n" +
                "  \"timeConflict\": false \n}";
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
        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"eventIDData\": 1,\n" +
                "  \"dateStart\": \"2023-06-08T12:15:22\",\n" +
                "  \"eventTime\": 1200,\n" +
                "  \"localization\": {\"id\": 1 },\n" +
                "  \"locationConflict\": false,\n" +
                "  \"timeConflict\": false \n}";
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