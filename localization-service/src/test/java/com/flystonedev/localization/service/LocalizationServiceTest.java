package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.LocalizationDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.mapper.LocalizationMapper;
import com.flystonedev.localization.mapper.LocalizationOutResponseMapper;
import com.flystonedev.localization.model.Localization;
import com.flystonedev.localization.repository.LocalizationRepository;
import com.flystonedev.localization.repository.MapImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalizationServiceTest implements SampleData {

    @InjectMocks
    private LocalizationService localizationService;

    @Mock
    private LocalizationRepository localizationRepository;

    @Mock
    private MapImageRepository mapImageRepository;

    private final LocalizationMapper localizationMapper = Mappers.getMapper(LocalizationMapper.class);
    private final LocalizationOutResponseMapper localizationOutResponseMapper = Mappers.getMapper(LocalizationOutResponseMapper.class);


    @Test
    void createLocalization() {
        //given
        LocalizationDTO excepted = getSampleOfLocalizationDTO();
        excepted.setId(null);
        when(mapImageRepository.findById(excepted.getMapImage().getId())).thenReturn(Optional.ofNullable(getSampleOfMapImage()));

        //when
        localizationService.createLocalization(excepted);
        //then

        ArgumentCaptor<Localization> argumentCaptor = ArgumentCaptor.forClass(Localization.class);
        verify(localizationRepository).save(argumentCaptor.capture());

        Localization value = argumentCaptor.getValue();
        assertThat(value).isEqualTo(localizationMapper.map(excepted));
    }

    @Test
    void localizationDTOList() {
        //given

        //when
        localizationService.localizationDTOList();
        //then
        verify(localizationRepository, times(1)).findAll();
        verifyNoMoreInteractions(localizationRepository);
    }

    @Test
    void get() {
        //given
        LocalizationDTO excepted = getSampleOfLocalizationDTO();
        when(localizationRepository.findById(anyInt())).thenReturn(Optional.ofNullable(localizationMapper.map(excepted)));
        //then
        LocalizationDTO actual = localizationService.get(excepted.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(localizationRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(localizationRepository);
    }

    @Test
    void update() {
        //given
        LocalizationDTO excepted = getSampleOfLocalizationDTO();
        when(localizationRepository.findById(anyInt())).thenReturn(Optional.ofNullable(localizationMapper.map(excepted)));
        when(localizationRepository.save(any(Localization.class))).thenReturn(localizationMapper.map(excepted));
        //when
        LocalizationDTO actual = localizationService.update(excepted);
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(localizationRepository, times(1)).save(any(Localization.class));
        verifyNoMoreInteractions(localizationRepository);
    }

    @Test
    void delete() {
        //given
        doNothing().when(localizationRepository).deleteById(anyInt());
        //when
        //then
        localizationService.delete(1);
        verify(localizationRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(localizationRepository);
    }

    @Test
    void getSimple() {
        //given
        LocalizationOutResponse excepted = getSampleLocalizationOutResponse();
        when(localizationRepository.findById(anyInt())).thenReturn(Optional.ofNullable(localizationOutResponseMapper.map(excepted)));
        //then
        LocalizationOutResponse actual = localizationService.getSimple(excepted.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(localizationRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(localizationRepository);
    }
}