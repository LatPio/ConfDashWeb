package com.flystonedev.event.clients;

import com.flystonedev.event.exeption.ClientCallException;
import com.flystonedev.event.exeption.config.GlobalErrorCode;
import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LocalizationClient {
    private final WebClient.Builder webClientBuilder;

    public LocalizationOutResponse localizationOutResponse (Integer id) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LocalizationOutResponse localizationOutResponse = webClientBuilder.build()
                .get()
                .uri("http://localization-Service/api/v1/localization/simple",
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

        BookingsDTO bookingsDTO = webClientBuilder.build()
                .post()
                .uri("http://localization-Service/api/v1/booking")
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

    public BookingsDTO getBookingsDTO (Integer id){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BookingsDTO bookingsDTO = webClientBuilder.build()
                .get()
                .uri("http://localization-Service/api/v1/booking")
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

    public BookingsDTO updateBookingsDTO (BookingsDTO bookingsToUpdate){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BookingsDTO bookingsDTO = webClientBuilder.build()
                .put()
                .uri("http://localization-Service/api/v1/booking")
                .body(Mono.just(bookingsToUpdate), BookingsDTO.class)
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
}
