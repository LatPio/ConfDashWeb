package com.flystonedev.event.clients;

import com.flystonedev.event.exeption.ClientCallException;
import com.flystonedev.event.exeption.config.GlobalErrorCode;
import com.flystonedev.event.DTO.LocalizationDTOs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class LocalizationClient {
    private final WebClient webClientBuilder;

    public LocalizationClient(WebClient.Builder builder, @Value("${urlLocalization}") String baseUrl){
        this.webClientBuilder = builder.baseUrl(baseUrl).build();
    }

    public LocalizationOutResponse localizationOutResponse (Integer id) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LocalizationOutResponse localizationOutResponse = webClientBuilder
                .get()
                .uri("/api/v1/localization/simple",
                        uriBuilder -> uriBuilder.queryParam("id" ,id).build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 400,
                        clientResponse -> {throw new ClientCallException("Provided Localization Id don't exist", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
                        clientResponse -> {throw new ClientCallException("Access to Localization service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
                        clientResponse -> {throw new ClientCallException("Endpoint to Localization Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .bodyToMono(LocalizationOutResponse.class)
                .block();
        return localizationOutResponse;
    }

    public BookingsDTO createBookingsDTO (BookingRequest bookingRequest){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BookingsDTO bookingsDTO = webClientBuilder
                .post()
                .uri("/api/v1/booking")
                .body(Mono.just(bookingRequest), BookingRequest.class)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
                        clientResponse -> {throw new ClientCallException("Access to Localization service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
                        clientResponse -> {throw new ClientCallException("Endpoint to Localization Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .bodyToMono(BookingsDTO.class)
                .block();
        return bookingsDTO;

    }

    public BookingsDTOLight getBookingsDTO (Integer id){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BookingsDTOLight bookingsDTO = webClientBuilder
                .get()
                .uri("/api/v1/booking/simple",
                        uriBuilder -> uriBuilder.queryParam("id", id).build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
                        clientResponse -> {throw new ClientCallException("Access to Localization service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
                        clientResponse -> {throw new ClientCallException("Endpoint to Localization Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .bodyToMono(BookingsDTOLight.class)
                .block();
        return bookingsDTO;

    }

    public BookingsDTOLight updateBookingsDTO (BookingsUpdateRequest bookingsToUpdate){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BookingsDTOLight bookingsDTO = webClientBuilder
                .put()
                .uri("/api/v1/booking/simple")
                .body(Mono.just(bookingsToUpdate), BookingsUpdateRequest.class)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
                        clientResponse -> {throw new ClientCallException("Access to Localization service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
                        clientResponse -> {throw new ClientCallException("Endpoint to Localization Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
                .bodyToMono(BookingsDTOLight.class)
                .block();
        return bookingsDTO;

    }
}
