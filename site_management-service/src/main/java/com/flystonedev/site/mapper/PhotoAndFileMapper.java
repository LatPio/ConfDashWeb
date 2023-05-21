package com.flystonedev.site.mapper;

import com.flystonedev.site.DTO.PhotosAndFilesDTO;
import com.flystonedev.site.model.PhotosAndFiles;
import org.mapstruct.Mapper;

@Mapper
public interface PhotoAndFileMapper {

    PhotosAndFilesDTO map(PhotosAndFiles photosAndFiles);
    PhotosAndFiles map(PhotosAndFilesDTO photosAndFilesDTO);
}
