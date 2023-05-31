package com.flystonedev.event.service;

import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.mapper.EventEntityMapper;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.model.EventType;
import com.flystonedev.event.repository.EventEntityRepository;
import com.flystonedev.event.repository.EventTypeRepository;
import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.LocalizationDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    @Transactional
    public void createEventEntity(EventEntityDTO eventEntityDTO){

        AbstractOutResponse abstractOutResponse = abstractOutResponse(Integer.valueOf(eventEntityDTO.getAbstractId()));
        LocalizationOutResponse localizationOutResponse = localizationOutResponse(Integer.valueOf(eventEntityDTO.getLocalizationId()));
        if(abstractOutResponse == null){
            throw new IllegalArgumentException("Provided Abstract Id don't exist");
        }
        if(abstractOutResponse == null){
            throw new IllegalArgumentException("Provided Localization Id don't exist");
        }

        if(abstractOutResponse != null && localizationOutResponse != null){
            EventEntity eventEntity = EventEntity.builder()
                    .name(abstractOutResponse.getAbstractTitle())
                    .abstractId(eventEntityDTO.getAbstractId())
                    .localizationId(eventEntityDTO.getLocalizationId())
                    .localizationName(localizationOutResponse.getRoom())
                    .eventType(eventTypeRepository.getReferenceById(eventEntityDTO.getEventType().getId()))
                    .dateTimeOfEvent(eventEntityDTO.getDateTimeOfEvent())
                    .build();

            EventEntity saved = eventEntityRepository.save(eventEntity);

            BookingRequest bookingRequest = BookingRequest.builder()
                    .eventIDData(saved.getId())
                    .dateStart(saved.getDateTimeOfEvent())
                    .localization(LocalizationDTO.builder().id(Integer.valueOf(saved.getLocalizationId())).build())
                    .eventTime(saved.getEventType().getTime())
                    .timeConflict(saved.getEventType().isTimeConflict())
                    .locationConflict(saved.getEventType().isLocationConflict())
                    .build();
            createBookingsDTO(bookingRequest);
        } else {
            throw new IllegalArgumentException("Provided Abstract and Localization Id don't exist");
        }

    }

    public List<EventEntityDTO> eventEntityDTOList() {
        List<EventEntity> eventEntityList = eventEntityRepository.findAll();
        return eventEntityList.stream().map(eventEntityMapper::map).collect(Collectors.toList());
    }

    public EventEntityDTO get(Integer id){
        return eventEntityRepository.findById(id).map(eventEntityMapper::map).orElse(null);
    }

    public EventEntityDTO update(EventEntityDTO eventEntityDTO){
        EventEntityDTO exist = get(eventEntityDTO.getId());
        if (exist == null) {
            return null;
        }
        EventEntity updated = eventEntityRepository.save(eventEntityMapper.map(eventEntityDTO));
        return eventEntityMapper.map(updated);
    }

    public void delete(Integer id){
        eventEntityRepository.deleteById(id);
    }

    public AbstractOutResponse abstractOutResponse(Integer id) throws RuntimeException{
        AbstractOutResponse abstractOutResponse = webClientBuilder.build().get()
                .uri("http://abstracts-Service/api/v1/abstracts/simple",
                        uriBuilder -> uriBuilder.queryParam("id", id.intValue()).build())
                .retrieve()
                .bodyToMono(AbstractOutResponse.class)
                .block();
        return abstractOutResponse;
    }

    public LocalizationOutResponse localizationOutResponse (Integer id) {
        LocalizationOutResponse localizationOutResponse = webClientBuilder.build()
                .get()
                .uri("http://localization-Service/api/v1/localization/simple",
                        uriBuilder -> uriBuilder.queryParam("id" ,id).build())
                .retrieve()
                .bodyToMono(LocalizationOutResponse.class)
                .block();
        return localizationOutResponse;
    }

    public BookingsDTO createBookingsDTO (BookingRequest bookingRequest){

        BookingsDTO bookingsDTO = webClientBuilder.build()
                .post()
                .uri("Http://localization-Service/api/v1/booking")
                .body(Mono.just(bookingRequest), BookingRequest.class)
                .retrieve()
                .bodyToMono(BookingsDTO.class)
                .block();
        return bookingsDTO;

    }
}
