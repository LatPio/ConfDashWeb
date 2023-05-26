package com.flystonedev.event.mapper;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.model.EventType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventEntityMapper {

    EventEntityDTO map(EventEntity eventEntity);
    EventEntity map(EventEntityDTO eventEntityDTO);
}
