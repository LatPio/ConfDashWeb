package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.MapImageDTO;
import com.flystonedev.localization.DTO.MapImageWithRoomsDTO;
import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.model.MapImage;
import com.flystonedev.localization.repository.MapImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class MapImageServiceTest implements SampleData {

    @InjectMocks
    private MapImageService mapImageService;
    @Mock
    private MapImageRepository mapImageRepository;

    @Test
    void saveMapImage() throws IOException {
        //give
        MapImageDTO expected = getSampleOfMapImageDTO();

        //when
        mapImageService.saveMapImage(getSampleMultipart(),getSampleOfMapImageRequest());
        //then

        ArgumentCaptor<MapImage> mapImageDTOArgumentCaptor = ArgumentCaptor.forClass(MapImage.class);
        verify(mapImageRepository).save(mapImageDTOArgumentCaptor.capture());

        MapImage mapImageArgumentCaptorValue = mapImageDTOArgumentCaptor.getValue();
        assertThat(mapImageArgumentCaptorValue.getFileName()).isEqualTo(expected.getFileName());
        assertThat(mapImageArgumentCaptorValue.getName()).isEqualTo(expected.getName());
        assertThat(mapImageArgumentCaptorValue.getData()).isEqualTo(expected.getData());


    }

    @Test
    void getMapImageDTO() {
        //given
        MapImageDTO expected = getSampleOfMapImageDTO();
        when(mapImageRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getSampleOfMapImage()));
        //when
        MapImageDTO actual = mapImageService.getMapImageDTO(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(mapImageRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(mapImageRepository);
    }

    @Test
    void getMapImageWithRoomsDTO() {
        //given
        MapImageWithRoomsDTO expected = getSampleOfMapImageWithRoomsDTO();
        when(mapImageRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getSampleOfMapImage()));
        //when
        MapImageWithRoomsDTO actual = mapImageService.getMapImageWithRoomsDTO(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(mapImageRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(mapImageRepository);
    }

    @Test
    void mapImageDTOSimpleList() {
        //given
        when(mapImageRepository.findAll()).thenReturn(getSampleOfListMapImage());
        //when
        List<MapImageDTO> actual = mapImageService.mapImageDTOList();
        //then
        verify(mapImageRepository, times(1)).findAll();
        verifyNoMoreInteractions(mapImageRepository);
        assertThat(actual).usingRecursiveComparison().isEqualTo(getSampleOfListMapImageDTO());
    }

    @Test
    void mapImageDTOList() {
        //given
        when(mapImageRepository.findAll()).thenReturn(getSampleOfListMapImage());
        //when
        List<MapImageDTO> actual = mapImageService.mapImageDTOList();
        //then
        verify(mapImageRepository, times(1)).findAll();
        verifyNoMoreInteractions(mapImageRepository);
        assertThat(actual).usingRecursiveComparison().isEqualTo(getSampleOfListMapImageDTO());
    }

    @Test
    void updateMapImage() throws IOException {
        //give
        MapImageDTO expected = getSampleOfMapImageDTO();
        when(mapImageRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getSampleOfMapImage()));

        //when
        mapImageService.updateMapImage(getSampleMultipart(),getSampleOfMapImageDTO());
        //then

        ArgumentCaptor<MapImage> mapImageDTOArgumentCaptor = ArgumentCaptor.forClass(MapImage.class);
        verify(mapImageRepository).save(mapImageDTOArgumentCaptor.capture());

        MapImage mapImageArgumentCaptorValue = mapImageDTOArgumentCaptor.getValue();
        assertThat(mapImageArgumentCaptorValue.getFileName()).isEqualTo(expected.getFileName());
        assertThat(mapImageArgumentCaptorValue.getName()).isEqualTo(expected.getName());
        assertThat(mapImageArgumentCaptorValue.getData()).isEqualTo(expected.getData());
    }

    @Test
    void deleteMApImage() {
        //given
        when(mapImageRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getSampleOfMapImage()));

        doNothing().when(mapImageRepository).deleteById(anyInt());
        //when
        mapImageService.deleteMApImage(1);
        //then
        verify(mapImageRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(mapImageRepository);
    }
}