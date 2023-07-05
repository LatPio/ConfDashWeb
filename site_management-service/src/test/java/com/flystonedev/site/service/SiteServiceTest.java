package com.flystonedev.site.service;

import com.flystonedev.site.DTO.SiteDTO;
import com.flystonedev.site.SampleData;
import com.flystonedev.site.mapper.SiteMapper;
import com.flystonedev.site.model.Site;
import com.flystonedev.site.repository.SiteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SiteServiceTest implements SampleData {

    @InjectMocks
    private SiteService service;
    @Mock
    private SiteRepository siteRepository;
    private final SiteMapper siteMapper = Mappers.getMapper(SiteMapper.class);


    @Test
    void createSite() {
        SiteDTO excepted = getSampleOfSiteDTO();
        excepted.setId(null);

        service.createSite(excepted);

        ArgumentCaptor<Site> abstractsEntityArgumentCaptor = ArgumentCaptor.forClass(Site.class);
        verify(siteRepository).save(abstractsEntityArgumentCaptor.capture());

        Site abstractsEntityArgumentCaptorValue = abstractsEntityArgumentCaptor.getValue();
        assertThat(abstractsEntityArgumentCaptorValue).isEqualTo(siteMapper.map(excepted));
    }

    @Test
    void siteDTOS() {
        //given

        //when
        service.siteDTOS();
        //then
        verify(siteRepository, times(1)).findAll();
        verifyNoMoreInteractions(siteRepository);
    }

    @Test
    void get() {
        //given
        SiteDTO excepted = getSampleOfSiteDTO();
        when(siteRepository.findById(anyInt())).thenReturn(Optional.ofNullable(siteMapper.map(excepted)));
        //when
        SiteDTO actual = service.get(1);
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(siteRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(siteRepository);
    }

    @Test
    void update() {
        SiteDTO excepted = getSampleOfSiteDTO();
        when(siteRepository.save(any(Site.class))).thenReturn(siteMapper.map(excepted));
        when(siteRepository.findById(excepted.getId())).thenReturn(Optional.ofNullable(siteMapper.map(excepted)));

        SiteDTO actual = service.update(excepted);
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(siteRepository, times(1)).save(any(Site.class));
        verifyNoMoreInteractions(siteRepository);

    }

    @Test
    void delete() {
        //given
        doNothing().when(siteRepository).deleteById(anyInt());
        //when
        //then
        service.delete(1);
        verify(siteRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(siteRepository);
    }
}