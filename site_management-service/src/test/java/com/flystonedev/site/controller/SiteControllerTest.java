package com.flystonedev.site.controller;

import com.flystonedev.site.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SiteControllerTest extends KeycloakTestContainers {

    @Test
    @Order(1)
    void registerSite() {
        String requestBody = "{\n" +
                "  \"name\": \"Name\",\n" +
                "  \"body\": \"Body of Site\",\n" +
                "  \"orderNumber\": 1,\n" +
                "  \"visible\": true \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/site")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    @Order(2)
    void listResponseEntity() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/site/list")
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
                .get("api/v1/site")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void update() {
        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"name\": \"Name\",\n" +
                "  \"body\": \"Body of Site\",\n" +
                "  \"orderNumber\": 1,\n" +
                "  \"visible\": true \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/site")
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
                .delete("api/v1/site")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}