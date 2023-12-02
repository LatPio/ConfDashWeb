package com.flystonedev.abstracts.clients;

import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.config.WebClientConfig;
import com.flystonedev.customer.DTO.CustomerDTO;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class CustomerClientTest  {
    @MockBean
    private CustomerClient customerClient;
    private MockWebServer server;

    @MockBean
    private JwtConverter jwtConverter;

    @MockBean
    private WebClientConfig webClientConfig;




    @BeforeEach
    public void setup() {
        this.server = new MockWebServer();
        this.customerClient = new CustomerClient(WebClient.builder(), server.url("/").toString(), jwtConverter);
    }
    @Test
    void getCustomer() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Jwt jwt = Mockito.mock(Jwt.class);
        JwtConverter jwtConverter = Mockito.mock(JwtConverter.class);
        when(authentication.getPrincipal()).thenReturn(jwt);
        when(jwtConverter.getJWTTokenValue()).thenReturn("asdf");

        server.enqueue(new MockResponse()
                .setStatus("HTTP/1.1 200")
                .addHeader("Content-Type", "application/json" )
                .setBody("""
                            "id": 1,
                            "firstName": "Piotr",
                            "lastName": "Łątka",
                            "email": "a@a.com",
                            "authID": "b039a821-d9a7-43b1-8345-b303942cb3b8",
                            "degree": "pHd",
                            "phoneNumber": "5555555",
                            "links": [],
                            "invoiceData": {"id":1},
                            "photo": {"id":1}
                        """)
        );

        CustomerDTO data = customerClient.getCustomer();
        assertThat(data.getId()).isEqualTo(1);
    }
}