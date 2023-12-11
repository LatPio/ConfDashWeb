package com.flystonedev.event;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.DTO.LocalizationDTOs.BookingsDTO;
import com.flystonedev.event.DTO.LocalizationDTOs.BookingsDTOLight;
import com.flystonedev.event.DTO.LocalizationDTOs.LocalizationDTO;
import com.flystonedev.event.DTO.LocalizationDTOs.LocalizationWithOutMapDTO;
import com.flystonedev.event.model.EventEntity;
import com.flystonedev.event.model.EventType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    default List<EventTypeDTO> getSampleOfEventTypeDTOList(){
        List<EventTypeDTO> list = new ArrayList<>();
        list.add(getSampleOfEventTypeDTO());
        return list;
    }

    default List<EventType> getSampleOfEventTypeList(){
        List<EventType> list = new ArrayList<>();
        list.add(getSampleOfEventType());
        return list;
    }

    default BookingsDTO getSampleOfBookingsDTO(){
        BookingsDTO bookingsDTO = BookingsDTO.builder()
                .id(1)
                .eventIDData(1)
                .locationConflict(false)
                .timeConflict(false)
                .dateStart(LocalDateTime.of(2023,10,1,11,0,0))
                .dateEnd(LocalDateTime.of(2023,10,1,12,0,0))
                .localization(LocalizationDTO.builder().id(1).build())
                .build();
        return bookingsDTO;
    }

    default BookingsDTOLight getSampleOfBookingsDTOLight(){
        BookingsDTOLight bookingsDTOLight = BookingsDTOLight.builder()
                .id(1)
                .eventIDData(1)
                .locationConflict(false)
                .timeConflict(false)
                .dateStart(LocalDateTime.of(2023,10,1,11,0,0))
                .dateEnd(LocalDateTime.of(2023,10,1,12,0,0))
                .localization(LocalizationWithOutMapDTO.builder().id(1).build())
                .build();
        return bookingsDTOLight;
    }
}
