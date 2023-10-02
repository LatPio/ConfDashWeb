package com.flystonedev.localization.mapper;

import com.flystonedev.localization.DTO.MapImageDTO;
import com.flystonedev.localization.DTO.MapImageResponse;
import com.flystonedev.localization.model.MapImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapImageMapper {

    MapImageDTO map(MapImage mapImage);
    MapImage map(MapImageDTO mapImageDTO);

    MapImageResponse mapResponse(MapImage mapImage);
}
