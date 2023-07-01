package com.flystonedev.customer.controller;

import com.flystonedev.customer.config.KeycloakTestContainers;
import com.flystonedev.customer.model.Customer;
import com.flystonedev.customer.repository.CustomerRepository;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProfilePhotoAdminControllerTest extends KeycloakTestContainers {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    void savePhoto() {

        customerRepository.save(Customer.builder().id(1).build());
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
//                .multiPart("data", body,"application/json")
                .param("id", "1")
                .when()
                .post("api/v1/admin/profile_photo")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(2)
    void getAdmin() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/admin/profile_photo")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test.txt", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void download() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
                .when()
                .get("api/v1/admin/profile_photo/file/1")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void getListFiles() {

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/admin/profile_photo/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void update() {

        customerRepository.save(Customer.builder().id(1).build());
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .multiPart("file", new File("./src/test/resources/test.txt"),"multipart/form-data")
                .param("id", "1")
                .when()
                .put("api/v1/admin/profile_photo")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }

//    @Test
//    @Order(6)
//    void delete() {
//        customerRepository.save(Customer.builder().id(1).build());
//
//        Response response = given()
//                .header("Authorization", getAccessToken("admin@email.com", "password"))
//                .param("id", "1")
//                .when()
//                .delete("api/v1/admin/profile_photo")
//                .peek()
//                .then()
//                .extract().response();
//        Assertions.assertEquals(200, response.statusCode());
//    }
}