package com.flystonedev.abstracts.mapper;

import com.flystonedev.abstracts.AbstractsApplication;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractLightDTO;
import com.flystonedev.abstracts.model.AbstractsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbstractMapper {

    AbstractDTO map(AbstractsEntity abstracts);
    AbstractsEntity map(AbstractDTO abstractDTO);


    AbstractLightDTO mapLight(AbstractsEntity abstracts);
    AbstractsEntity mapLight(AbstractLightDTO abstractLightDTO);

}
