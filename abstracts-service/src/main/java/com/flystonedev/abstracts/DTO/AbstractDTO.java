package com.flystonedev.abstracts.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO {
    private Integer id;
    private String abstractTitle;
    private String body;
    private byte[] graphicalAbstract ;
    private String authors;
    private String affiliation;
    private byte[] figure;
    private Integer presenterId;
    private Integer ownerId;
    private boolean accepted;
    private byte[] abstractFile;
}
