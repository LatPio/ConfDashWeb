package com.flystonedev.event;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.model.EventType;

import java.time.LocalDateTime;
import java.util.Date;

public interface SampleData {

    default EventEntity getSampleOfEventEntity(){
        EventEntity eventEntity = EventEntity.builder()
                .id(1)
                .name("Name")
                .ownerId(1)
                .abstractName("Abstract")
                .abstractId("1")
                .localizationId("1")
                .localizationName("Room 1")
                .bookingId(1)
                .eventType(getSampleOfEventType())
                .startOfEvent(LocalDateTime.of(2023,10,1,11,0,0))
                .endOfEvent(LocalDateTime.of(2023,10,1,12,0,0))
                .build();
        return eventEntity;
    }

    default EventEntityDTO getSampleOfEventEntityDTO(){
        EventEntityDTO eventEntityDTO = EventEntityDTO.builder()
                .id(1)
                .name("Name")
                .ownerId(1)
                .abstractName("Abstract")
                .abstractId("1")
                .localizationId("1")
                .localizationName("Room 1")
                .bookingId(1)
                .eventType(getSampleOfEventTypeDTO())
                .startOfEvent(LocalDateTime.of(2023,10,1,11,0,0))
                .endOfEvent(LocalDateTime.of(2023,10,1,12,0,0))
                .build();
        return eventEntityDTO;
    }

    default EventType getSampleOfEventType(){
        EventType eventType = EventType.builder()
                .id(1)
                .name("Presentation")
                .timeInMinutes(20)
                .locationConflict(false)
                .timeConflict(false)
                .eventEntity(null)
                .build();
        return eventType;
    }

    default EventTypeDTO getSampleOfEventTypeDTO(){
        EventTypeDTO eventTypeDTO = EventTypeDTO.builder()
                .id(1)
                .name("Presentation")
                .timeInMinutes(20)
                .locationConflict(false)
                .timeConflict(false)
                .build();
        return eventTypeDTO;
    }
}
