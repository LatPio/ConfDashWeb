package com.flystonedev.event.service;

import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.mapper.EventEntityMapper;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.repository.EventEntityRepository;
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

        AbstractOutResponse response = webClientBuilder.build().get()
                .uri("http://abstracts-Service/api/v1/abstracts/simple",
                        uriBuilder -> uriBuilder.queryParam("id", eventEntityDTO.getAbstractId()).build())
                .retrieve()
                .bodyToMono(AbstractOutResponse.class)
                .block();

        if(response != null){
            EventEntity eventEntity = EventEntity.builder()
                    .name(response.getAbstractTitle())
                    .abstractId(eventEntityDTO.getAbstractId())
                    .localizationId(eventEntityDTO.getLocalizationId())
                    .eventType(eventTypeMapper.map(eventEntityDTO.getEventType()))
                    .dateTimeOfEvent(eventEntityDTO.getDateTimeOfEvent())
                    .build();

            eventEntityRepository.save(eventEntity);
        } else {
            throw new IllegalArgumentException("Provided Abstract Id dont exist");
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

}
