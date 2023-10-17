package com.flystonedev.abstracts.clients;

import com.flystonedev.customer.DTO.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CustomerClient {

    private final WebClient.Builder webClient;


    public CustomerDTO getCustomer(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerDTO customerDTO = webClient.build()
                .get()
                .uri("http://customer-service/api/v1/user/customer")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .block();

        return customerDTO;


    }
}
