package com.flystonedev.localization.mapper;

import com.flystonedev.localization.DTO.LocalizationWithOutMapDTO;
import com.flystonedev.localization.model.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalizationWithOutMapMapper {

    LocalizationWithOutMapDTO map(Localization localization);

    Localization map(LocalizationWithOutMapDTO localizationWithOutMapDTO);
}
