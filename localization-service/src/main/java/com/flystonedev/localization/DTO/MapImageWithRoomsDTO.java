package com.flystonedev.localization.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapImageWithRoomsDTO {

    private Integer id;
    private String name;
    private String fileName;
    private List<LocalizationWithOutMapDTO> localization;
    private byte[] data;
}
