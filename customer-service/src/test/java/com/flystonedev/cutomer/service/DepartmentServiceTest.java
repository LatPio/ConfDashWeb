package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.DepartmentDTO;
import com.flystonedev.cutomer.SampleData;
import com.flystonedev.cutomer.mapper.DepartmentMapper;
import com.flystonedev.cutomer.model.Department;
import com.flystonedev.cutomer.repository.DepartmentRepository;
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
class DepartmentServiceTest implements SampleData {
    @InjectMocks
    DepartmentService departmentService;

    @Mock
    DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    @Test
    void addDepartment() {
        //given
        DepartmentDTO toSave = getSampleOfDepartmentDTO();
        //When
        departmentService.addDepartment(toSave);
        //Then
        verify(departmentRepository, times(1)).save(any());
    }

    @Test
    void departmentResponseList() {
        departmentRepository.findAll();
        verify(departmentRepository, times(1)).findAll();
        verifyNoMoreInteractions(departmentRepository);
    }

    @Test
    void get() {
        DepartmentDTO excepted = getSampleOfDepartmentDTO();
        when(departmentRepository.findById(excepted.getId())).thenReturn(Optional.ofNullable(departmentMapper.map(excepted)));

        departmentService.get(excepted.getId());

        verify(departmentRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(departmentRepository);
    }

    @Test
    void update() {
        var toSave = getSampleOfDepartmentDTO();
        when(departmentRepository.save(any(Department.class))).thenReturn(departmentMapper.map(toSave));
        when(departmentRepository.findById(toSave.getId())).thenReturn(Optional.ofNullable(departmentMapper.map(toSave)));

        var updated = departmentService.update(toSave);

        assertThat(updated).usingRecursiveComparison().isEqualTo(toSave);
        verify(departmentRepository, times(1)).save(any(Department.class));
        verifyNoMoreInteractions(departmentRepository);

    }

    @Test
    void delete() {
        //given
        doNothing().when(departmentRepository).deleteById(anyInt());
        //when
        //then
        departmentService.delete(1);
        verify(departmentRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(departmentRepository);
    }
}