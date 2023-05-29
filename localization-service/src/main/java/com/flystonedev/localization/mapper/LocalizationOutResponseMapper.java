package com.flystonedev.localization.mapper;

import com.flystonedev.localization.DTO.LocalizationOutResponse;
import com.flystonedev.localization.model.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalizationOutResponseMapper {

    LocalizationOutResponse map(Localization localization);

    Localization map(LocalizationOutResponse localizationOutResponse);
}
