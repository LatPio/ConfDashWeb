package com.flystonedev.event.mapper;

import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.model.EventType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {

    EventTypeDTO map(EventType eventType);
    EventType map(EventTypeDTO eventTypeDTO);

}
