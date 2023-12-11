package com.flystonedev.event.controller;

import com.flystonedev.event.DTO.AbstractDTOs.AbstractOutResponse;
import com.flystonedev.event.DTO.LocalizationDTOs.BookingsDTO;
import com.flystonedev.event.DTO.LocalizationDTOs.BookingsDTOLight;
import com.flystonedev.event.DTO.LocalizationDTOs.LocalizationOutResponse;
import com.flystonedev.event.DTO.LocalizationDTOs.LocalizationWithOutMapDTO;
import com.flystonedev.event.SampleData;
import com.flystonedev.event.clients.AbstractClient;
import com.flystonedev.event.clients.LocalizationClient;
import com.flystonedev.event.config.KeycloakTestContainers;
import com.flystonedev.event.exeption.EntityNotFoundException;
import com.flystonedev.event.repository.EventEntityRepository;
import com.flystonedev.event.repository.EventTypeRepository;
import com.flystonedev.event.service.EventEntityService;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class EventEntityControllerTest extends KeycloakTestContainers implements SampleData {

    @Autowired
    private EventEntityRepository eventEntityRepository;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private EventTypeRepository eventTypeRepository;
    @MockBean
    private AbstractClient abstractClient;
    @MockBean
    private LocalizationClient localizationClient;
    @BeforeEach
    void setUp(){
        eventTypeRepository.save(getSampleOfEventType());
    }

    @Test
    @Order(2)
    void registerEvent() {

        when(abstractClient.abstractOutResponse(anyInt())).thenReturn(AbstractOutResponse.builder().id(1).abstractTitle("Abstract").build());
        when(localizationClient.localizationOutResponse(anyInt())).thenReturn(LocalizationOutResponse.builder().id(1).room("Room 1").build());
        when(localizationClient.createBookingsDTO(any())).thenReturn(BookingsDTO.builder().id(1).build());


        String requestBody = """
                {
                    "abstractId": 1,
                    "localizationId":1,
                    "eventType":{"id":1},
                    "startOfEvent": "2023-12-07T18:46:06"
                }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/event")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Event created successfully", response.getBody().prettyPrint());

    }


    @Test
    @Order(1)
    void fallbackRegisterEvent() {

        when(abstractClient.abstractOutResponse(1)).thenThrow(new EntityNotFoundException());
        when(localizationClient.localizationOutResponse(1)).thenThrow(new EntityNotFoundException());
        when(localizationClient.createBookingsDTO(any())).thenThrow(new EntityNotFoundException());
        String requestBody = """
                 {
                }
                """;
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/event")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Something gone wrong, probably:  Cannot parse null string, or some features may be off-line try later.", response.getBody().prettyPrint());
    }

    @Test
    @Order(3)
    void list() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .when()
                .get("api/v1/event/list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    void userList() {
        String requestBody = """
                [1]
                """;
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/event/user-list")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    void listByRoom() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/event/list/room")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(6)
    void get() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .get("api/v1/event")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(7)
    void update(){
        when(abstractClient.abstractOutResponse(anyInt())).thenReturn(AbstractOutResponse.builder().id(1).abstractTitle("Abstract").build());
        when(localizationClient.localizationOutResponse(anyInt())).thenReturn(LocalizationOutResponse.builder().id(1).room("Room 1").build());
        when(localizationClient.getBookingsDTO(any())).thenReturn(BookingsDTOLight.builder().id(1).localization(LocalizationWithOutMapDTO.builder().id(1).build()).build());


        String requestBody = """
                {
                        "id": 1,
                        "name": "jjjasdfasdfj",
                        "ownerId": 1,
                        "abstractName": "asdfasd",
                        "abstractId": "1",
                        "localizationId": "1",
                        "localizationName": "asdf",
                        "bookingId": 1,
                        "eventType": {
                            "id": 1,
                            "name": "Presentation",
                                        "timeInMinutes": 20,
                                        "locationConflict": false,
                                        "timeConflict": false
                        },
                        "startOfEvent": "2023-11-02T09:38:14",
                        "endOfEvent": "2023-12-02T11:18:14"
                    }""";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .and()
                .body(requestBody)
                .when()
                .put("api/v1/event")
                .then()
                .extract().response().peek();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("jjjasdfasdfj", response.jsonPath().getString("name"));
    }

    @Test
    @Order(8)
    void delete() {
        Response response = given()
                .header("Authorization", getAccessToken("admin@email.com", "password"))
                .param("id", "1")
                .when()
                .delete("api/v1/event")
                .peek()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }


}