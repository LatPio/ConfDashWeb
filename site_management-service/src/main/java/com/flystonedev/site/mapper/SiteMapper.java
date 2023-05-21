package com.flystonedev.site.mapper;

import com.flystonedev.site.DTO.SiteDTO;
import com.flystonedev.site.model.Site;
import org.mapstruct.Mapper;

@Mapper
public interface SiteMapper {

    SiteDTO map(Site site);
    Site map(SiteDTO siteDTO);
}
