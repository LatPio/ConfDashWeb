package com.flystonedev.site.mapper;

import com.flystonedev.site.DTO.FilesDTO;
import com.flystonedev.site.model.Files;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilesMapper {

    FilesDTO map(Files files);
    Files map(FilesDTO filesDTO);
}
