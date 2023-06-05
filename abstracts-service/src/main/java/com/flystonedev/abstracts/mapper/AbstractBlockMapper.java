package com.flystonedev.abstracts.mapper;

import com.flystonedev.abstracts.DTO.AbstractBlockDTO;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.model.AbstractsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbstractBlockMapper {

    AbstractBlockDTO map(AbstractsEntity abstracts);
    AbstractsEntity map(AbstractBlockDTO abstractBlockDTO);

}
