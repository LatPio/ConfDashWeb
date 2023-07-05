package com.flystonedev.site.service;

import com.flystonedev.site.DTO.FilesDTO;
import com.flystonedev.site.SampleData;
import com.flystonedev.site.mapper.FilesMapper;
import com.flystonedev.site.model.Files;
import com.flystonedev.site.repository.FilesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilesServiceTest implements SampleData {

    @InjectMocks
    private FilesService filesService;
    @Mock
    private FilesRepository filesRepository;

    private final FilesMapper filesMapper = Mappers.getMapper(FilesMapper.class);


    @Test
    void saveFile() throws IOException {
        MultipartFile sampleMultipart = getSampleMultipart();
        Files excepted = getSampleOfFiles();
        excepted.setId(null);
        filesService.saveFile(sampleMultipart);

        ArgumentCaptor<Files> attachmentFileArgumentCaptor = ArgumentCaptor.forClass(Files.class);
        verify(filesRepository).save(attachmentFileArgumentCaptor.capture());

        Files attachmentFileArgumentCaptorValue = attachmentFileArgumentCaptor.getValue();
        assertThat(attachmentFileArgumentCaptorValue).isEqualTo(excepted);
    }

    @Test
    void filesDTOList() {
        filesService.filesDTOList();
        verify(filesRepository, times(1)).findAll();
        verifyNoMoreInteractions(filesRepository);
    }

    @Test
    void get() {
        //given
        final var expected = getSampleOfFilesDTO();
        when(filesRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(filesMapper.map(expected)));
        //when
        final var actual = filesService.get(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(filesRepository, times(1)).findById(expected.getId());
        verifyNoMoreInteractions(filesRepository);
    }

    @Test
    void update() {
        Files excepted = getSampleOfFiles();
        when(filesRepository.findById(excepted.getId())).thenReturn(Optional.of(excepted));
        when(filesRepository.save(any(Files.class))).thenReturn(excepted);

        FilesDTO actual = filesService.update(getSampleOfFilesDTO());
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(filesRepository, times(1)).save(any(Files.class));
        verifyNoMoreInteractions(filesRepository);

    }

    @Test
    void delete() {
        doNothing().when(filesRepository).deleteById(anyInt());

        //when
        filesService.delete(1);
        //then
        verify(filesRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(filesRepository);
    }
}