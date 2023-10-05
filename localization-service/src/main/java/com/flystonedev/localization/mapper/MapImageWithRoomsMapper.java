package com.flystonedev.localization.mapper;

import com.flystonedev.localization.DTO.MapImageWithRoomsDTO;
import com.flystonedev.localization.model.MapImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapImageWithRoomsMapper {

    MapImageWithRoomsDTO map(MapImage mapImage);

    MapImage map(MapImageWithRoomsDTO mapImageWithRoomsDTO);
}
