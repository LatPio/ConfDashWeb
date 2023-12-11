package com.flystonedev.event.DTO.LocalizationDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalizationOutResponse {
    private Integer id;
    private String room;
}
