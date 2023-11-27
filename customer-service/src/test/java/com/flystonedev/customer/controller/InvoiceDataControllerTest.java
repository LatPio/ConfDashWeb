package com.flystonedev.customer.controller;

import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.config.KeycloakTestContainers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InvoiceDataControllerTest extends KeycloakTestContainers implements SampleData {

    @BeforeEach
    void setUp() {
    }
    @Test
    @Order(1)
    void addDepartment() {
        String requestBody = """
                {
                  "name": "Some Department",
                  "street": "first Street",
                  "buildingNumber": "12B",
                  "flatNumber": "f1-23",
                  "city": "NY",
                  "postalCode": "12345-1",
                  "country": "USA",
                  "taxIdentificationNumber": "12345-1",
                  "institutionShortName": "short",
                  "institution": "Some Institution"\s
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/invoice")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Some Department", response.jsonPath().getString("name"));
        Assertions.assertEquals("first Street", response.jsonPath().getString("street"));
        Assertions.assertEquals("12B", response.jsonPath().getString("buildingNumber"));
        Assertions.assertEquals("f1-23", response.jsonPath().getString("flatNumber"));
        Assertions.assertEquals("NY", response.jsonPath().getString("city"));
        Assertions.assertEquals("12345-1", response.jsonPath().getString("postalCode"));
        Assertions.assertEquals("USA", response.jsonPath().getString("country"));
        Assertions.assertEquals("12345-1", response.jsonPath().getString("taxIdentificationNumber"));
        Assertions.assertEquals("short", response.jsonPath().getString("institutionShortName"));
        Assertions.assertEquals("Some Institution", response.jsonPath().getString("institution"));


    }

    @Test
    @Order(2)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/invoice")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Some Department", response.jsonPath().getString("name"));

    }

    @Test
    @Order(3)
    void list() {

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/invoice/list")
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
                  "name": "Some New Department",
                  "street": "first Street",
                  "buildingNumber": "12B",
                  "flatNumber": "f1-23",
                  "city": "NY",
                  "postalCode": "12345-1",
                  "country": "New USA",
                  "taxIdentificationNumber": "12345-1",
                  "institutionShortName": "New short",
                  "institution": "Some New Institution"\s
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/invoice")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Some New Department", response.jsonPath().getString("name"));
        Assertions.assertEquals("first Street", response.jsonPath().getString("street"));
        Assertions.assertEquals("12B", response.jsonPath().getString("buildingNumber"));
        Assertions.assertEquals("f1-23", response.jsonPath().getString("flatNumber"));
        Assertions.assertEquals("NY", response.jsonPath().getString("city"));
        Assertions.assertEquals("12345-1", response.jsonPath().getString("postalCode"));
        Assertions.assertEquals("New USA", response.jsonPath().getString("country"));
        Assertions.assertEquals("12345-1", response.jsonPath().getString("taxIdentificationNumber"));
        Assertions.assertEquals("New short", response.jsonPath().getString("institutionShortName"));
        Assertions.assertEquals("Some New Institution", response.jsonPath().getString("institution"));
    }

    @Test
    @Order(5)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/invoice")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}