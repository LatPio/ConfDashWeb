package com.flystonedev.site.service;

import com.flystonedev.site.DTO.SiteDTO;
import com.flystonedev.site.exeption.EntityNotFoundException;
import com.flystonedev.site.mapper.SiteMapper;
import com.flystonedev.site.model.Site;
import com.flystonedev.site.repository.SiteRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;
    private final SiteMapper siteMapper = Mappers.getMapper(SiteMapper.class);

    public void createSite(SiteDTO siteDTO){
        Site site = Site.builder()
                .name(siteDTO.getName())
                .body(siteDTO.getBody())
                .build();
        siteRepository.save(site);
    }
    public List<SiteDTO> siteDTOS() {
        List<Site> sitesList = siteRepository.findAll();
        return sitesList.stream().map(siteMapper::map).collect(Collectors.toList());
    }
    public SiteDTO get(Integer id){
        return siteRepository.findById(id).map(siteMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public SiteDTO update(SiteDTO siteDTO){
        SiteDTO exist = get(siteDTO.getId());
        if (exist == null){
            return null;
        }
        Site updated = siteRepository.save(siteMapper.map(siteDTO));
        return siteMapper.map(updated);
    }

    public void delete(Integer id){
        siteRepository.deleteById(id);
    }

}
