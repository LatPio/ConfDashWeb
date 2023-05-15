package com.flystonedev.cutomer.mapper;


import com.flystonedev.cutomer.model.InformationLinks;
import com.flystonedev.cutomer.records.InformationLinksResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformationLinksMapper {

    InformationLinksResponse map(InformationLinks informationLinks);

    InformationLinks map(InformationLinksResponse informationLinksResponse);
}
