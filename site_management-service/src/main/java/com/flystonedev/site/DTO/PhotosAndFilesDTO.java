package com.flystonedev.site.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotosAndFilesDTO {
    private Integer id;
    private String name;
    private byte[] file;
}
