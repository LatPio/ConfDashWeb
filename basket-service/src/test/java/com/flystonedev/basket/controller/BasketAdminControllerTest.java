package com.flystonedev.basket.controller;

import com.flystonedev.basket.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BasketAdminControllerTest extends KeycloakTestContainers  {

    @Test
    @Order(1)
    void addAbstract() {
        String requestBody = "{\n" +
                "  \"name\": \"Name of Item In Basket\",\n" +
                "  \"eventId\": 1,\n" +
                "  \"deletable\": true,\n" +
                "  \"authId\": \"aaaa-bbbb\" \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/admin/basket")
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Name of Item In Basket", response.jsonPath().getString("name"));
    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .param("authId", "aaaa-bbbb")
                .when()
                .get("api/v1/admin/basket")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Name of Item In Basket", response.jsonPath().getString("name"));

    }

    @Test
    @Order(3)
    void list() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("authId", "aaaa-bbbb")
                .when()
                .get("api/v1/admin/basket/list")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .param("authId", "aaaa-bbbb")
                .when()
                .delete("api/v1/admin/basket")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }
}