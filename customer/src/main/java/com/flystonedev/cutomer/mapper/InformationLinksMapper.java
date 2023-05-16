package com.flystonedev.cutomer.mapper;


import com.flystonedev.cutomer.DTO.InformationLinksDTO;
import com.flystonedev.cutomer.model.InformationLinks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformationLinksMapper {

    InformationLinksDTO map(InformationLinks informationLinks);

    InformationLinks map(InformationLinksDTO informationLinksDTO);
}
