package com.flystonedev.event.DTO.LocalizationDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapImageDTO {

    private Integer id;
    private String name;
    private String fileName;
    private byte[] data;
}
