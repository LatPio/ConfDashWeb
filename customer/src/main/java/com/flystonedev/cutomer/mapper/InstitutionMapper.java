package com.flystonedev.cutomer.mapper;

import com.flystonedev.cutomer.DTO.InstitutionDTO;
import com.flystonedev.cutomer.model.Institution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface InstitutionMapper {

    InstitutionDTO map(Institution institution);

    Institution map(InstitutionDTO institutionDTO);
}
