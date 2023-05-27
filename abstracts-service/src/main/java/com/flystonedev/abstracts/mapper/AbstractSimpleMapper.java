package com.flystonedev.abstracts.mapper;

import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.model.AbstractsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbstractSimpleMapper {

    AbstractOutResponse map(AbstractsEntity abstracts);
    AbstractsEntity map(AbstractOutResponse abstractOutResponse);

}
