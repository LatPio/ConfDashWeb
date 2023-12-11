package com.flystonedev.basket.DTO.EventDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventTypeDTO {

    private Integer id;
    private String name;
    private Integer timeInMinutes;
    private boolean locationConflict;
    private boolean timeConflict;
}
