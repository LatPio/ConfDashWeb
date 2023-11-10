package com.flystonedev.event.service;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.StatisticResponse;
import com.flystonedev.event.clients.AbstractClient;
import com.flystonedev.event.clients.LocalizationClient;
import com.flystonedev.event.exeption.EntityNotFoundException;
import com.flystonedev.event.mapper.EventEntityMapper;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.repository.EventEntityRepository;
import com.flystonedev.event.repository.EventTypeRepository;
import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.LocalizationDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventEntityService {

    private final EventEntityRepository eventEntityRepository;
    private final EventTypeRepository eventTypeRepository;
    private final EventEntityMapper eventEntityMapper = Mappers.getMapper(EventEntityMapper.class);

    private final EventTypeMapper eventTypeMapper = Mappers.getMapper(EventTypeMapper.class);

    private final WebClient.Builder webClientBuilder;

    private AbstractClient abstractClient;
    private final LocalizationClient localizationClient;
    @Transactional
    public void createEventEntity(EventEntityDTO eventEntityDTO){

            EventEntity eventEntity = EventEntity.builder()
                    .name(eventEntityDTO.getName())
                    .ownerId(abstractClient.abstractOutResponse(Integer.valueOf(eventEntityDTO.getAbstractId())).getOwnerId())
                    .abstractName(abstractClient.abstractOutResponse(Integer.valueOf(eventEntityDTO.getAbstractId())).getAbstractTitle())
                    .abstractId(eventEntityDTO.getAbstractId())
                    .localizationId(eventEntityDTO.getLocalizationId())
                    .localizationName(localizationClient.localizationOutResponse(Integer.valueOf(eventEntityDTO.getLocalizationId())).getRoom())
                    .eventType(eventTypeRepository.getReferenceById(eventEntityDTO.getEventType().getId()))
                    .startOfEvent(eventEntityDTO.getStartOfEvent())
                    .endOfEvent(eventEntityDTO.getStartOfEvent().plusMinutes(eventEntityDTO.getEventType().getTime().toMinutes()).minusSeconds(1L))

                    .build();

        EventEntity saved = eventEntityRepository.save(eventEntity);

        BookingRequest bookingRequest = BookingRequest.builder()
                    .eventIDData(saved.getId())
                    .dateStart(saved.getStartOfEvent())
                    .eventTime(saved.getEventType().getTime())
                    .localization(LocalizationDTO.builder().id(Integer.valueOf(saved.getLocalizationId())).build())
                    .timeConflict(saved.getEventType().isTimeConflict())
                    .locationConflict(saved.getEventType().isLocationConflict())
                    .build();
        BookingsDTO savedBooking = localizationClient.createBookingsDTO(bookingRequest);

        saved.setBookingId(savedBooking.getId());
        eventEntityRepository.save(saved);


    }

    public List<EventEntityDTO> eventEntityDTOList() {
        List<EventEntity> eventEntityList = eventEntityRepository.findAll();
        return eventEntityList.stream().map(eventEntityMapper::map).collect(Collectors.toList());
    }

    public List<EventEntityDTO> eventEntityDTOListByLocalization(String id) {
        List<EventEntity> eventEntityList = eventEntityRepository.findEventEntitiesByLocalizationId(id);
        return eventEntityList.stream().map(eventEntityMapper::map).collect(Collectors.toList());
    }

    public EventEntityDTO get(Integer id){
        return eventEntityRepository.findById(id).map(eventEntityMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public EventEntityDTO update(EventEntityDTO eventEntityDTO){
        EventEntityDTO exist = get(eventEntityDTO.getId());
        if (exist == null) {
            return null;
        }
        EventEntity updated = eventEntityRepository.save(eventEntityMapper.map(eventEntityDTO));

        BookingsDTO bookingToUpdate = localizationClient.getBookingsDTO(exist.getBookingId());
        bookingToUpdate.setDateStart(eventEntityDTO.getStartOfEvent());
        bookingToUpdate.setDateEnd(eventEntityDTO.getEndOfEvent());
        BookingsDTO updatedBooking = localizationClient.updateBookingsDTO(bookingToUpdate);

        return eventEntityMapper.map(updated);


    }

    public void delete(Integer id){
        eventEntityRepository.deleteById(id);
    }

    public StatisticResponse statistic(){
        return new StatisticResponse(eventEntityRepository.count(), eventTypeRepository.count());

    }


//    public AbstractOutResponse abstractOutResponse(Integer id){
//        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        AbstractOutResponse abstractOutResponse = webClientBuilder.build()
//                .get()
//                .uri("http://abstracts-Service/api/v1/admin/abstracts/simple",
//        uriBuilder -> uriBuilder.queryParam("id", id.intValue()).build())
//
//                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
//                .retrieve()
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 400,
//                        clientResponse -> {throw new ClientCallException("Provided Abstract Id don't exist", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
//                        clientResponse -> {throw new ClientCallException("Access to Abstract service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
//                        clientResponse -> {throw new ClientCallException("Endpoint to Abstract Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
//                .bodyToMono(AbstractOutResponse.class)
//                .block();
//
//        return abstractOutResponse;
//    }

//    public LocalizationOutResponse localizationOutResponse (Integer id) {
//        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        LocalizationOutResponse localizationOutResponse = webClientBuilder.build()
//                .get()
//                .uri("http://localization-Service/api/v1/localization/simple",
//                        uriBuilder -> uriBuilder.queryParam("id" ,id).build())
//                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
//                .retrieve()
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 400,
//                            clientResponse -> {throw new ClientCallException("Provided Localization Id don't exist", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
//                        clientResponse -> {throw new ClientCallException("Access to Localization service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
//                        clientResponse -> {throw new ClientCallException("Endpoint to Localization Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
//                .bodyToMono(LocalizationOutResponse.class)
//                .block();
//        return localizationOutResponse;
//    }
//
//    public BookingsDTO createBookingsDTO (BookingRequest bookingRequest){
//        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        BookingsDTO bookingsDTO = webClientBuilder.build()
//                .post()
//                .uri("Http://localization-Service/api/v1/booking")
//                .body(Mono.just(bookingRequest), BookingRequest.class)
//                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
//                .retrieve()
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 401,
//                        clientResponse -> {throw new ClientCallException("Access to Localization service Denied", GlobalErrorCode.ERROR_EVENT_SERVICE_ACCESS_DENIED);})
//                .onStatus(  httpStatusCode-> httpStatusCode.value() == 404,
//                        clientResponse -> {throw new ClientCallException("Endpoint to Localization Service not found", GlobalErrorCode.ERROR_EVENT_SERVICE_ENTITY_NOT_FOUND);})
//                .bodyToMono(BookingsDTO.class)
//                .block();
//        return bookingsDTO;
//
//    }
}
