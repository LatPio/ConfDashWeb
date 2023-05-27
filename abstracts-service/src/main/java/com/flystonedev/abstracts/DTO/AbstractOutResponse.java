package com.flystonedev.abstracts.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbstractOutResponse {

    private Integer id;
    private String abstractTitle;
    private String body;
    private Integer presenterId;
}
