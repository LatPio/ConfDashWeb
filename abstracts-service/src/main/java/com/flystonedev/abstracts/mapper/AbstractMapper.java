package com.flystonedev.abstracts.mapper;

import com.flystonedev.abstracts.AbstractsApplication;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.model.AbstractsEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AbstractMapper {

    AbstractDTO map(AbstractsEntity abstracts);

    AbstractsEntity map(AbstractDTO abstractDTO);
}
