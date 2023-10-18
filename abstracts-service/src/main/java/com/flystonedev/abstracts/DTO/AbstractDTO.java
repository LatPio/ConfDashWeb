package com.flystonedev.abstracts.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO {
    private Integer id;
    private String abstractTitle;
    private String body;
    private String authors;
    private String affiliation;
    private Integer ownerId;
    private String authId;
    private boolean accepted;
    private List<AttachmentFileDTO> files;
    private String comments;
}
