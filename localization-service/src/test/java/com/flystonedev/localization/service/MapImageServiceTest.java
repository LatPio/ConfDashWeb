package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.MapImageDTO;
import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.mapper.MapImageMapper;
import com.flystonedev.localization.mapper.MapImageWithRoomsMapper;
import com.flystonedev.localization.model.MapImage;
import com.flystonedev.localization.repository.MapImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class MapImageServiceTest implements SampleData {

    @InjectMocks
    private MapImageService mapImageService;

    @Mock
    private MapImageRepository mapImageRepository;

    private final MapImageMapper mapImageMapper = Mappers.getMapper(MapImageMapper.class);

    private final MapImageWithRoomsMapper mapImageWithRoomsMapper = Mappers.getMapper(MapImageWithRoomsMapper.class);

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
    }

    @Test
    void getMapImageWithRoomsDTO() {
    }

    @Test
    void mapImageDTOSimpleList() {
    }

    @Test
    void mapImageDTOList() {
    }

    @Test
    void updateMapImage() {
    }

    @Test
    void deleteMApImage() {
    }
}