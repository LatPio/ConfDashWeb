package com.flystonedev.customer.controller;

import com.flystonedev.customer.config.KeycloakTestContainers;
import com.flystonedev.customer.model.Customer;
import com.flystonedev.customer.repository.CustomerRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class InformationLinksControllerTest extends KeycloakTestContainers {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        customerRepository.save(Customer.builder()
                .id(1)
                .build());
    }

    @Test
    @Order(1)
    void addInformationLink() {
        String requestBody = """
                {
                  "name": "LinkedIn",
                  "urlLink": "http....",
                  "customer": {"id": "1" }
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/user/info_links")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().getString("authId"));
    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/user/info_links")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("LinkedIn", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void informationLinksUsersList() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/user/info_links/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void update() {
        String requestBody = """
                {
                  "id": "1",
                  "name": "LinkedIn",
                  "urlLink": "some new address",
                  "customer": {"id": "1" }
                }""";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/user/info_links")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("some new address", response.jsonPath().getString("urlLink"));
    }

    @Test
    @Order(5)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/user/info_links")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}