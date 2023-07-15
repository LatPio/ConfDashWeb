//package com.flystonedev.event.clients;
//
//import static org.junit.jupiter.api.Assertions.*;
//import com.flystonedev.abstracts.DTO.AbstractOutResponse;
//import com.flystonedev.event.exeption.ClientCallException;
//import com.flystonedev.event.exeption.config.GlobalErrorCode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
//
//import java.net.URI;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@AutoConfigureWebTestClient
//class AbstractClientTest {
//
////    @Autowired
////    private WebTestClient webTestClient;
//
//    @Autowired
//    private AbstractClient abstractClient;
//    @Mock
//    private WebClient.Builder webClientBuilder;
//
//    @Test
//    void abstractOutResponseTest_WithValidId_ReturnsAbstractOutResponse() {
//        // Arrange
//        Integer id = 123;
//
//        AbstractOutResponse expectedResponse = AbstractOutResponse.builder()
//                .id(123)
//                .abstractTitle("Asdf")
//                .body("asdfa")
//                .presenterId(1)
//                .build();
//
//        // Mock the WebClient.Builder
//        WebClient.Builder webClientBuilderMock = mock(WebClient.Builder.class);
//        when(webClientBuilderMock.build()).thenReturn(WebClient.builder().build());
//        when(webClientBuilderMock.baseUrl("http://abstracts-Service/api/v1/admin/abstracts"))
//                .thenReturn(webClientBuilderMock);
//        when(webClientBuilderMock.build().get()
//                .uri(uriBuilder -> uriBuilder.queryParam("id", id.intValue()).build()))
//                .thenReturn(webClientBuilderMock);
//        when(webClientBuilderMock.build().get()
//                .uri(uriBuilder -> uriBuilder.queryParam("id", id.intValue()).build())
//                .retrieve()
//                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> {
//                    throw new ClientCallException("Provided Abstract Id don't exist",
//                            GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);
//                })
//                .onStatus(HttpStatus.UNAUTHORIZED::equals, response -> {
//                    throw new ClientCallException("Access to Abstract service Denied",
//                            GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);
//                })
//                .onStatus(HttpStatus.NOT_FOUND::equals, response -> {
//                    throw new ClientCallException("Endpoint to Abstract Service not found",
//                            GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);
//                })
//                .bodyToMono(AbstractOutResponse.class).block())
//                .thenReturn(expectedResponse);
//
//        // Set the mocked WebClient.Builder in the AbstractClient
////        abstractClient.setWebClientBuilder(webClientBuilderMock);
//
//        // Act
//        AbstractOutResponse response = abstractClient.abstractOutResponse(id);
//
//        // Assert
//        assertEquals(expectedResponse, response);
//    }
//    @Test
//    void abstractOutResponseTest_WithValidId_ReturnsAbstractOutResponse() {
//        // Arrange
//        Integer id = 123;
//        AbstractOutResponse expectedResponse = new AbstractOutResponse();
//
//        MockitoAnnotations.openMocks(this);
//
//        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
//        WebClient.RequestHeadersSpec<?> requestHeadersSpecMock = mock(WebClient.RequestHeadersSpec.class);
//        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);
//
//        when(webClientBuilder.build()).thenReturn(webClientBuilder);
//        when(webClientBuilder.get()).thenReturn(requestHeadersUriSpecMock);
//        when(requestHeadersUriSpecMock.uri(anyString(), any())).thenReturn(requestHeadersSpecMock);
//        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
//        when(responseSpecMock.onStatus(any(), any())).thenReturn(responseSpecMock);
//        when(responseSpecMock.bodyToMono(AbstractOutResponse.class)).thenReturn(Mono.just(expectedResponse));
//
//        AbstractClient abstractClient = new AbstractClient(webClientBuilder);
//
//        // Act
//        AbstractOutResponse response = abstractClient.abstractOutResponseTest(id, "http://example.com");
//
//        // Assert
//        assertEquals(expectedResponse, response);
//
//        // Verify the interactions with the mocked objects
//        verify(webClientBuilder).build();
//        verify(webClientBuilder).get();
//        verify(requestHeadersUriSpecMock).uri(eq("http://example.com/api/v1/admin/abstracts/simple"), any());
//        verify(requestHeadersSpecMock).retrieve();
//        verify(responseSpecMock).onStatus(eq(HttpStatus.BAD_REQUEST), any());
//        verify(responseSpecMock).onStatus(eq(HttpStatus.UNAUTHORIZED), any());
//        verify(responseSpecMock).onStatus(eq(HttpStatus.NOT_FOUND), any());
//        verify(responseSpecMock).bodyToMono(AbstractOutResponse.class);
//    }
//
////    @TestConfiguration
////    static class TestConfig {
//////        @Bean
//////        @Primary
////        public WebClient.Builder webClientBuilder() {
////            return WebClient.builder();
////        }
////    }
//}