package com.flystonedev.event.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventTypeDTO {

    private Integer id;
    private String name;
    private Duration time;
    private boolean locationConflict;
    private boolean timeConflict;
}
