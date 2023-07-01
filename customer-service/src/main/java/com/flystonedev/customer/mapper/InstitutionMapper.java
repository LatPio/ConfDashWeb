package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.InstitutionDTO;
import com.flystonedev.customer.model.Institution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    InstitutionDTO map(Institution institution);

    Institution map(InstitutionDTO institutionDTO);
}
