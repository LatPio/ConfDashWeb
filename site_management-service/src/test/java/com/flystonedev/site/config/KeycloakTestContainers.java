package com.flystonedev.site.config;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import io.restassured.RestAssured;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class KeycloakTestContainers {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakTestContainers.class.getName());

    @LocalServerPort
    private int port;
    String authServer = keycloak.getAuthServerUrl();

    static final KeycloakContainer keycloak;

    static {
        keycloak = new KeycloakContainer().withRealmImportFile("realmsTest/realm-tests.json");
        keycloak.start();
    }

    @PostConstruct
    public void init() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @DynamicPropertySource
    static void registerResourceServerIssuerProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () -> keycloak.getAuthServerUrl() + "realms/confdashweb");
    }

    @Test
    public void testKc() {
        assertTrue(keycloak.isRunning());

        String authServer = keycloak.getAuthServerUrl();
        System.out.println(authServer);

        String token = given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(Map.of(
                        "username", "admin@email.com",
                        "password", "password",
                        "grant_type","password",
                        "client_id","spring-cloud-client",
                        "client_secret", "secret",
                        "scope","openid"
                ))
                .post(authServer + "realms/confdashweb/protocol/openid-connect/token")
                .then().assertThat().statusCode(200)
                .extract().path("access_token");
        System.out.println(token);
    }


    protected String getAccessToken(String username, String password) {
        String token = given().contentType("application/x-www-form-urlencoded")
                .formParams(Map.of(
                        "username", username,
                        "password", password,
                        "grant_type", "password",
                        "scope", "openid",
                        "client_id", "spring-cloud-client",
                        "client_secret", "secret"
                ))
                .post(authServer + "realms/confdashweb/protocol/openid-connect/token")
//                .post(authServer + "realms/confdashweb/protocol/openid-connect/token")
                .then().assertThat().statusCode(200)
                .extract().path("access_token");
        return "Bearer " + token;
    }

    protected String getTokenAdminByKeycloakAPI() {

        try (Keycloak keycloakAdminClient = KeycloakBuilder.builder()
                .serverUrl(keycloak.getAuthServerUrl())
                .realm("confdashweb")
                .clientId("spring-cloud-client")
                .clientSecret("secret")
                .scope("openid")
                .username("admin@email.com")
                .password("password")
                .build()) {

            String access_token = keycloakAdminClient.tokenManager().getAccessToken().getToken();
            return "Bearer " + access_token;
        } catch (Exception e) {
            LOGGER.error("Can't obtain an access token from Keycloak!", e);
        }
        return null;
    }

    protected String getTokenAdminByWebclient() {


        try {
            URI authorizationURI = new URIBuilder(keycloak.getAuthServerUrl() + "realms/confdashweb/protocol/openid-connect/token").build();
            WebClient webclient = WebClient.builder()
                    .build();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.put("grant_type", Collections.singletonList("password"));
            formData.put("client_id", Collections.singletonList("spring-cloud-client"));
            formData.put("client_secret", Collections.singletonList("secret"));
            formData.put("scope", Collections.singletonList("openid"));
            formData.put("username", Collections.singletonList("admin@email.com"));
            formData.put("password", Collections.singletonList("password"));

            String result = webclient.post()
                    .uri(authorizationURI)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JacksonJsonParser jsonParser = new JacksonJsonParser();

            return "Bearer " + jsonParser.parseMap(result)
                    .get("access_token")
                    .toString();
        } catch (URISyntaxException e) {
            LOGGER.error("Can't obtain an access token from Keycloak!", e);
        }

        return null;
    }




}
