package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.config.KeycloakTestContainers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class AbstractAdminControllerTest extends KeycloakTestContainers {

    @Test
    void addAbstract() {
    }

    @Test
    void get() {
    }

    @Test
    void getSimple() {
    }

    @Test
    void list() {
    }

    @Test
    void updateAdmin() {
    }

    @Test
    void updateBlockEdit() {
    }

    @Test
    void delete() {
    }

    @Test
    void getPrincipal() {

         given()
                 .header("Authorization", getAccessToken("admin@email.com", "password"))
                 .when()
                 .get("api/v1/admin/abstracts/list")
                 .peek()
                 .then()
                 .statusCode(200);
//                .body("username", equalTo("admin@email.com"));

    }
}