package com.flystonedev.event.service;

import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.mapper.EventEntityMapper;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.repository.EventEntityRepository;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
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
    private final EventEntityMapper eventEntityMapper = Mappers.getMapper(EventEntityMapper.class);

    private final EventTypeMapper eventTypeMapper = Mappers.getMapper(EventTypeMapper.class);

    private final WebClient.Builder webClientBuilder;

    public void createEventEntity(EventEntityDTO eventEntityDTO){

        AbstractOutResponse abstractOutResponse = abstractOutResponse(Integer.valueOf(eventEntityDTO.getAbstractId()));
        LocalizationOutResponse localizationOutResponse = localizationOutResponse(Integer.valueOf(eventEntityDTO.getLocalizationId()));


        if(abstractOutResponse != null && localizationOutResponse != null){
            EventEntity eventEntity = EventEntity.builder()
                    .name(abstractOutResponse.getAbstractTitle())
                    .abstractId(eventEntityDTO.getAbstractId())
                    .localizationId(eventEntityDTO.getLocalizationId())
                    .localizationName(localizationOutResponse.getRoom())
                    .eventType(eventTypeMapper.map(eventEntityDTO.getEventType()))
                    .dateTimeOfEvent(eventEntityDTO.getDateTimeOfEvent())
                    .build();

            eventEntityRepository.save(eventEntity);
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

    public AbstractOutResponse abstractOutResponse(Integer id){
        AbstractOutResponse abstractOutResponse = webClientBuilder.build().get()
                .uri("http://abstracts-Service/api/v1/abstracts/simple",
                        uriBuilder -> uriBuilder.queryParam("id", id.intValue()).build())
                .retrieve()
                .bodyToMono(AbstractOutResponse.class)
                .block();
        return abstractOutResponse;
    }

    public LocalizationOutResponse localizationOutResponse (Integer id){
        LocalizationOutResponse localizationOutResponse = webClientBuilder.build()
                .get()
                .uri("http://localization-Service/api/v1/localization/simple",
                        uriBuilder -> uriBuilder.queryParam("id" ,id).build())
                .retrieve()
                .bodyToMono(LocalizationOutResponse.class)
                .block();
        return localizationOutResponse;
    }
}
