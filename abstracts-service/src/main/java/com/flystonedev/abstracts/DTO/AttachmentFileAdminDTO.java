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
public class AttachmentFileAdminDTO {

    private Integer id;
    private String name;
    private String type;
    private String authId;
    private FileRole fileRole;
    private Boolean accepted;
    private byte[] data;
    private byte[] smallData;
    private AbstractDTO abstractsEntity;
}
