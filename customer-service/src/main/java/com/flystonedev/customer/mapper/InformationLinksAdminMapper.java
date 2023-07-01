package com.flystonedev.customer.mapper;


import com.flystonedev.customer.DTO.InformationLinksAdminDTO;
import com.flystonedev.customer.model.InformationLinks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformationLinksAdminMapper {

    InformationLinksAdminDTO map(InformationLinks informationLinks);

    InformationLinks map(InformationLinksAdminDTO informationLinksAdminDTO);
}
