package com.flystonedev.event.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntityDTO {

    private Integer id;
    private String name;
    private Integer ownerId;
    private String abstractName;
    private String abstractId;
    private String localizationId;
    private String localizationName;
    private Integer bookingId;
    private EventTypeDTO eventType;
    private LocalDateTime dateTimeOfEvent;
}
