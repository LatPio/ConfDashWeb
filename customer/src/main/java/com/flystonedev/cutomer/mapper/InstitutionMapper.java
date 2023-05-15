package com.flystonedev.cutomer.mapper;

import com.flystonedev.cutomer.model.Institution;
import com.flystonedev.cutomer.records.InstitutionRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    InstitutionRecord map(Institution institution);

    Institution map(InstitutionRecord institutionRecord);
}
