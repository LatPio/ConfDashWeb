package com.flystonedev.customer.controller;

import com.flystonedev.customer.config.KeycloakTestContainers;
import com.flystonedev.customer.model.Customer;
import com.flystonedev.customer.repository.CustomerRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InformationLinksAdminControllerTest extends KeycloakTestContainers {

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
        String requestBody = "{\n" +
                "  \"name\": \"LinkedIn\",\n" +
                "  \"urlLink\": \"http....\",\n" +
                "  \"customer\": {\"id\": \"1\" },\n" +
                "  \"authId\": \"authorization id\" \n}";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/admin/info_links")
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
                .get("api/v1/admin/info_links")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("LinkedIn", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void informationLinksAdminResponseList() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/admin/info_links/list")
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
                "  \"name\": \"LinkedIn\",\n" +
                "  \"urlLink\": \"some new address\",\n" +
                "  \"customer\": {\"id\": \"1\" },\n" +
                "  \"authId\": \"authorization id\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/admin/info_links")
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
                .delete("api/v1/admin/info_links")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}