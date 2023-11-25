package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.mapper.AbstractMapper;
import com.flystonedev.abstracts.repository.AbstractRepository;
import com.flystonedev.abstracts.service.AbstractService;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AbstractUserControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private AbstractRepository abstractRepository;

    @MockBean
    private AbstractService abstractService;

    @Autowired
    private AbstractMapper abstractMapper = Mappers.getMapper(AbstractMapper.class);
    @BeforeEach
    void setUp() {

        abstractRepository.saveAll(getSampleAbstract());

    }
    Integer startId = null;
    @Test
    @Order(1)
    void addAbstract() {
        String requestBody = "{\n" +
                "  \"abstractTitle\": \"Abstract New Title\",\n" +
                "  \"body\": \"Body of Abstract\",\n" +
                "  \"authors\": \"Barack Obama\",\n" +
                "  \"affiliation\": \"Washington DC\",\n" +
                "  \"ownerId\": \"2\",\n" +
                "  \"authId\": \"vava-dddd\",\n" +
                "  \"accepted\": \"false\",\n" +
                "  \"files\": [],\n" +
                "  \"comments\": \"comments\" \n}";

        when(abstractService.createUserAbstract(any())).thenReturn(getSampleOfOneAbstractDTO());


        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Title: Sample Abstract", response.jsonPath().getString("abstractTitle"));
        Assertions.assertEquals(getSampleOfOneAbstractDTO(), response.as(AbstractDTO.class));
    }

    @Test
    @Order(2)
    void get() {

        when(abstractService.getUsersAbstract(1)).thenReturn(getSampleOfOneAbstractDTO());

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Title: Sample Abstract", response.jsonPath().getString("abstractTitle"));
        Assertions.assertEquals(getSampleOfOneAbstractDTO(), response.as(AbstractDTO.class));

    }

    @Test
    @Order(3)
    void list() {
        List<AbstractDTO> expected = getSampleAbstract().stream().map(abstractsEntity -> abstractMapper.map(abstractsEntity)).collect(Collectors.toList());
        when(abstractService.abstractUsersDTOList()).thenReturn(expected);

        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/user/abstracts/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(expected.size(), response.as(List.class).size());


    }

    @Test
    @Order(4)
    void listAccepted() {
        List<AbstractDTO> expected = getSampleAbstract().stream().map(abstractsEntity -> abstractMapper.map(abstractsEntity)).collect(Collectors.toList());
        when(abstractService.abstractUserAcceptedDTOList()).thenReturn(expected);
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/user/abstracts/listAccepted")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(expected.size(), response.as(List.class).size());

    }

    @Test
    @Order(5)
    void update() {
        when(abstractService.updateUsersAbstract(any())).thenReturn(getSampleOfOneAbstractDTO());


        String requestBody = "{\n" +
                "  \"id\": \"12\",\n" +
                "  \"abstractTitle\": \"Abstract New Title\",\n" +
                "  \"body\": \"Body of Abstract\" \n" +
                " \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Title: Sample Abstract", response.jsonPath().getString("abstractTitle"));
        Assertions.assertEquals(getSampleOfOneAbstractDTO(), response.as(AbstractDTO.class));
    }

    @Test
    @Order(6)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "12")
                .when()
                .delete("api/v1/user/abstracts")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}