package com.flystonedev.event.service;

import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.exeption.EntityNotFoundException;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventType;
import com.flystonedev.event.repository.EventTypeRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final EventTypeMapper eventTypeMapper = Mappers.getMapper(EventTypeMapper.class);

    public EventTypeDTO createEventType(EventTypeDTO eventTypeDTO){
        EventType eventType = EventType.builder()
                .name(eventTypeDTO.getName())
                .timeInMinutes(eventTypeDTO.getTimeInMinutes())
                .timeConflict(eventTypeDTO.isTimeConflict())
                .locationConflict(eventTypeDTO.isLocationConflict())
                .build();
        EventType newEventType = eventTypeRepository.save(eventType);
        return eventTypeMapper.map(newEventType);
    }

    public List<EventTypeDTO> eventTypeDTOList(){
        List<EventType> eventTypeList = eventTypeRepository.findAll();

        return eventTypeList.stream().map(eventTypeMapper::map).collect(Collectors.toList());
    }

    public EventTypeDTO get(Integer id){
        return eventTypeRepository.findById(id).map(eventTypeMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public EventTypeDTO update(EventTypeDTO eventTypeDTO){
        EventTypeDTO exist = get(eventTypeDTO.getId());
        if (exist == null) {
            return null;
        }
        EventType updated = eventTypeRepository.save(eventTypeMapper.map(eventTypeDTO));
        return eventTypeMapper.map(updated);
    }

    public void delete(Integer id){
        eventTypeRepository.deleteById(id);
    }



}
