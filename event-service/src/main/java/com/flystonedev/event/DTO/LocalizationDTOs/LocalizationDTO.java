package com.flystonedev.event.DTO.LocalizationDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDTO {

    private Integer id;
    private String room;
    private Integer coordinateX;
    private Integer coordinateY;
    private MapImageDTO mapImage;
}
