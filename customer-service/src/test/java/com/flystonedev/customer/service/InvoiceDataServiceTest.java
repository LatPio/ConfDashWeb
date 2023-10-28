package com.flystonedev.customer.service;

import com.flystonedev.customer.DTO.InvoiceDataDTO;
import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.mapper.InvoiceDataMapper;
import com.flystonedev.customer.model.InvoiceData;
import com.flystonedev.customer.repository.InvoiceDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceDataServiceTest implements SampleData {
    @InjectMocks
    InvoiceDataService invoiceDataService;

    @Mock
    InvoiceDataRepository departmentRepository;
    private final InvoiceDataMapper departmentMapper = Mappers.getMapper(InvoiceDataMapper.class);

    @Test
    void addDepartment() {
        //given
        InvoiceDataDTO toSave = getSampleOfDepartmentDTO();
        //When
        invoiceDataService.addDepartment(toSave);
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
        InvoiceDataDTO excepted = getSampleOfDepartmentDTO();
        when(departmentRepository.findById(excepted.getId())).thenReturn(Optional.ofNullable(departmentMapper.map(excepted)));

        invoiceDataService.get(excepted.getId());

        verify(departmentRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(departmentRepository);
    }

    @Test
    void update() {
        var toSave = getSampleOfDepartmentDTO();
        when(departmentRepository.save(any(InvoiceData.class))).thenReturn(departmentMapper.map(toSave));
        when(departmentRepository.findById(toSave.getId())).thenReturn(Optional.ofNullable(departmentMapper.map(toSave)));

        var updated = invoiceDataService.update(toSave);

        assertThat(updated).usingRecursiveComparison().isEqualTo(toSave);
        verify(departmentRepository, times(1)).save(any(InvoiceData.class));
        verifyNoMoreInteractions(departmentRepository);

    }

    @Test
    void delete() {
        //given
        doNothing().when(departmentRepository).deleteById(anyInt());
        //when
        //then
        invoiceDataService.delete(1);
        verify(departmentRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(departmentRepository);
    }
}