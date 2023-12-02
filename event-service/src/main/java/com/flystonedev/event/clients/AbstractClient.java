package com.flystonedev.event.clients;

import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.event.exeption.ClientCallException;
import com.flystonedev.event.exeption.config.GlobalErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AbstractClient {

    private final WebClient webClient;


    public AbstractClient(WebClient.Builder builder,@Value("${urlAbstract}") String baseUrl) {
        this.webClient =  builder
                .baseUrl(baseUrl)
                .build();
    }

    public AbstractOutResponse abstractOutResponse(Integer id){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AbstractOutResponse abstractOutResponse = webClient
                .get()

                .uri("/api/v1/admin/abstracts/simple",
                        uriBuilder -> uriBuilder.queryParam("id", id).build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 400,
                        clientResponse -> {throw new ClientCallException("Provided Abstract Id don't exist", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
                        clientResponse -> {throw new ClientCallException("Access to Abstract service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
                        clientResponse -> {throw new ClientCallException("Endpoint to Abstract Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .bodyToMono(AbstractOutResponse.class)
                .block()
                ;

        return abstractOutResponse;
    }

}
