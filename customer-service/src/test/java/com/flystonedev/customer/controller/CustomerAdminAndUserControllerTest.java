package com.flystonedev.customer.controller;

import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.config.KeycloakTestContainers;
import com.flystonedev.customer.repository.CustomerRepository;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerAdminAndUserControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    @Order(1)
    void createNewUser(){
        String requestBody = "{\n" +
                "  \"firstName\": \"Joanne\",\n" +
                "  \"lastName\": \"Dark\",\n" +
                "  \"email\": \"joannedark@email.com\",\n" +
                "  \"password\": \"pass\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/user/customer/new")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
//        Assertions.assertEquals("+44123456789", response.jsonPath().getString("phoneNumber"));

    }

    @Test
    @Order(2)

    void canAdminGetUserInformation() {

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()

                .get("api/v1/admin/customer")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Joanne", response.jsonPath().getString("firstName"));
    }
    @Test
    @Order(3)

    void canUserGetAnyUsersSimpleInformation() {

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/user/customer/card")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Joanne", response.jsonPath().getString("firstName"));
    }

//    @Test
//    @Ignore
//    @Order(4)
//    void canUserGetOwnUserInformation() {
//
//        Response response = given()
//                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
//                .when()
//
//                .get("api/v1/user/customer")
//                .peek()
//                .then()
//                .extract().response();
//        Assertions.assertEquals(200, response.statusCode());
//        Assertions.assertEquals("Joanne", response.jsonPath().getString("firstName"));
//    }

    @Test
    @Order(5)
    void canAdminGetListOfUsers() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/admin/customer/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
    @Test
    @Order(6)
    void canUserGetListOfUsersCard() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/user/customer/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(7)

    void canAdminUpdateUsersInformation() {
        String requestBody = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"phoneNumber\": \"+44123456789\",\n" +
                "  \"degree\": \"PhD\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/admin/customer")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("+44123456789", response.jsonPath().getString("phoneNumber"));
    }
//    @Test
//    @Ignore
//    @Order(8)
//    void canUserUpdateOwnUsersInformation() {
//        String requestBody = "{\n" +
//                "  \"id\": \"1\",\n" +
//                "  \"phoneNumber\": \"+44123456789\",\n" +
//                "  \"degree\": \"PhD\" \n}";
//
//        Response response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .and()
//                .body(requestBody)
//                .when()
//                .put("api/v1/user/customer")
//                .peek()
//                .then()
//                .extract().response();
//
//        Assertions.assertEquals(200, response.statusCode());
//        Assertions.assertEquals("+44123456789", response.jsonPath().getString("phoneNumber"));
//    }

    @Test
    @Order(9)

    void canAdminDeleteUserFromDatabase() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/admin/customer")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}