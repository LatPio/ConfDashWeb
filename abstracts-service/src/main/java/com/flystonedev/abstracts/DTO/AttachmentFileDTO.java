package com.flystonedev.abstracts.DTO;

import com.flystonedev.abstracts.model.FileRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentFileDTO {

    private Integer id;
    private String name;
    private String type;
    private FileRole fileRole;
    private byte[] data;
//    private AbstractDTO abstractsEntity;
}