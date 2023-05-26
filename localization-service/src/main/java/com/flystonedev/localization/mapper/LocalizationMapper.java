package com.flystonedev.localization.mapper;

import com.flystonedev.localization.DTO.LocalizationDTO;
import com.flystonedev.localization.model.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalizationMapper {

    LocalizationDTO map(Localization localization);
    Localization map(LocalizationDTO localizationDTO);

}
