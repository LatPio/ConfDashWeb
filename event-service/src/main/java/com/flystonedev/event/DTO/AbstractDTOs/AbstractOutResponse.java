package com.flystonedev.event.DTO.AbstractDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbstractOutResponse {

    private Integer id;
    private String abstractTitle;
    private String body;
    private Integer ownerId;
}
