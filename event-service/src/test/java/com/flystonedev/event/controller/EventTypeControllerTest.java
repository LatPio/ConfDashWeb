package com.flystonedev.event.controller;

import com.flystonedev.event.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventTypeControllerTest extends KeycloakTestContainers {

    @Test
    @Order(1)
    void registerEventType() {
        String requestBody = """
                {
                  "name": "Name of Event-Type",
                  "timeInMinutes": 20,
                  "locationConflict": false,
                  "timeConflict": true
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/event-type")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Name of Event-Type", response.jsonPath().getString("name"));
    }

    @Test
    @Order(2)
    void list() {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/event-type/list")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(3)
    void get() {

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .and()
                .when()
                .get("api/v1/event-type")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Name of Event-Type", response.jsonPath().getString("name"));
    }

    @Test
    @Order(4)
    void update() {
        String requestBody = """
                {
                  "id":1,
                  "name": "Name of Event-Type",
                  "timeInMinutes": 20,
                  "locationConflict": false,
                  "timeConflict": true
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/event-type")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Name of Event-Type", response.jsonPath().getString("name"));

    }

    @Test
    @Order(5)
    void delete() {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .and()
                .when()
                .delete("api/v1/event-type")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }
}