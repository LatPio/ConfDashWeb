package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.DepartmentDTO;
import com.flystonedev.cutomer.DTO.InstitutionDTO;
import com.flystonedev.cutomer.SampleData;
import com.flystonedev.cutomer.mapper.InstitutionMapper;
import com.flystonedev.cutomer.repository.InstitutionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstitutionServiceTest implements SampleData {

    @InjectMocks
    private InstitutionService institutionService;

    @Mock
    private InstitutionRepository institutionRepository;

    private final InstitutionMapper institutionMapper = Mappers.getMapper(InstitutionMapper.class);

    @Test
    void canAddInstitution() {
        //given
        InstitutionDTO sampleOfInstitutionDTO = getSampleOfInstitutionDTO();
        when(institutionRepository.findInstitutionByName(sampleOfInstitutionDTO.getName())).thenReturn(null);
        //When
        institutionService.addInstitution(sampleOfInstitutionDTO);
        //Then
        verify(institutionRepository, times(1)).save(any());
        verifyNoMoreInteractions(institutionRepository);
    }

    @Test
    void canGetInstitutionRecordList() {
        //given

        //when
        institutionService.institutionRecordList();
        //then
        verify(institutionRepository, times(1)).findAll();
        verifyNoMoreInteractions(institutionRepository);
    }

    @Test
    void canGetInstitution() {
        //given
        InstitutionDTO sampleOfInstitutionDTO = getSampleOfInstitutionDTO();
        when(institutionRepository.findById(sampleOfInstitutionDTO.getId())).thenReturn(Optional.ofNullable(institutionMapper.map(sampleOfInstitutionDTO)));
        //when
        institutionService.get(sampleOfInstitutionDTO.getId());
        //then
        verify(institutionRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(institutionRepository);
    }

    @Test
    void canUpdateInstitution() {
        //given
        InstitutionDTO sampleOfInstitutionDTO = getSampleOfInstitutionDTO();
        when(institutionRepository.findById(sampleOfInstitutionDTO.getId())).thenReturn(Optional.ofNullable(institutionMapper.map(sampleOfInstitutionDTO)));
        when(institutionRepository.save(institutionMapper.map(sampleOfInstitutionDTO))).thenReturn(institutionMapper.map(sampleOfInstitutionDTO));
        //when
        InstitutionDTO update = institutionService.update(sampleOfInstitutionDTO);
        //then
        assertThat(update).usingRecursiveComparison().isEqualTo(sampleOfInstitutionDTO);
        verify(institutionRepository, times(1)).save(institutionMapper.map(sampleOfInstitutionDTO));
        verifyNoMoreInteractions(institutionRepository);

    }

    @Test
    void delete() {
        doNothing().when(institutionRepository).deleteById(anyInt());
        InstitutionDTO sampleOfInstitutionDTO = getSampleOfInstitutionDTO();
        institutionService.delete(sampleOfInstitutionDTO.getId());

        verify(institutionRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(institutionRepository);
    }
}