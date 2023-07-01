package com.flystonedev.customer.mapper;


import com.flystonedev.customer.DTO.InformationLinksDTO;
import com.flystonedev.customer.model.InformationLinks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformationLinksMapper {

    InformationLinksDTO map(InformationLinks informationLinks);

    InformationLinks map(InformationLinksDTO informationLinksDTO);
}
