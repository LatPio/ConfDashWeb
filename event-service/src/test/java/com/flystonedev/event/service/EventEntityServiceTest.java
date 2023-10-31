package com.flystonedev.event.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.event.clients.AbstractClient;
import lombok.RequiredArgsConstructor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RequiredArgsConstructor
class EventEntityServiceTest {
    @Mock
    private JwtConverter jwtConverter;

//    @Autowired
//    private WebTestClient webTestClient;

    private WebClient.Builder webClient = WebClient.builder();


    public static MockWebServer mockBackEnd;
    @Autowired
    private AbstractClient abstractClient;
    private ObjectMapper MAPPER = new ObjectMapper();



//    @BeforeAll
//    void setUp() throws IOException {
//        mockBackEnd = new MockWebServer();
//        mockBackEnd.start();
//    }
//    @AfterAll
//    static void tearDown() throws IOException {
//        mockBackEnd.shutdown();
//    }
//    @BeforeEach
//    void initialize() {
//        String baseUrl = String.format("http://localhost:%s",
//                mockBackEnd.getPort());
//        abstractClient = new AbstractClient(WebClient.builder());
//    }

    @BeforeEach
    void setUP() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();


//        webClient = webClient.baseUrl(mockBackEnd.url("/").toString());


    }

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();

    }

    @Test
    void createEventEntity() {
    }

    @Test
    void eventEntityDTOList() {
    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void abstractOutResponse() throws Exception {
        Jwt jwt = Jwt.withTokenValue("test_token")
                .header("alg", "HS256")
                .claim("sub", "testuser")
                .claim("authorities", Collections.singletonList("ROLE_ADMIN"))
                .build();

        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        AbstractOutResponse abstractOutResponse = AbstractOutResponse.builder()
                .id(1)
                .abstractTitle("new Abstract")
                .body("body of abstract")
                .ownerId(1)
                .build();

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(jwt,jwt.getTokenValue());

        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockBackEnd.enqueue(new MockResponse().setResponseCode(200).setBody(MAPPER.writeValueAsString(abstractOutResponse)).addHeader("Content-Type", "application/json"));
//        AbstractOutResponse abstractOutResponse1 = abstractClient.abstractOutResponseTest(1, baseUrl);
        System.out.println(
//                abstractOutResponse1
        );

    }

    @Test
    void localizationOutResponse() {
    }

    @Test
    void createBookingsDTO() {
    }
}