package com.flystonedev.abstracts.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractLightDTO {

    private Integer id;
    private String abstractTitle;
    private String body;
    private String authors;
    private String affiliation;
    private Integer presenterId;
    private Integer ownerId;
    private String authId;
    private String comments;

    private boolean accepted;
}
