package com.flystonedev.basket.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {

    private Integer id;
    private String name;
    private Integer eventId;
    private Boolean deletable;
    private String authId;
}
