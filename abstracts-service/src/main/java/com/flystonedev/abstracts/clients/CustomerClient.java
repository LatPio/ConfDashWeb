package com.flystonedev.abstracts.clients;

import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.customer.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomerClient {

    private final WebClient webClient;
    private final JwtConverter jwtConverter;

    public CustomerClient(WebClient.Builder builder, @Value("${urlCustomer}") String baseUrl, JwtConverter jwtConverter){
        this.webClient = builder.baseUrl(baseUrl).build();
        this.jwtConverter = jwtConverter;
    }

    public CustomerDTO getCustomer(){
        String token = jwtConverter.getJWTTokenValue();
        CustomerDTO customerDTO = webClient
                .get()
                .uri("http://customer-service/api/v1/user/customer/simple")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .block();
        return customerDTO;
    }
}
