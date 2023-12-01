package com.flystonedev.event.service;

import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.SampleData;
import com.flystonedev.event.clients.AbstractClient;
import com.flystonedev.event.clients.LocalizationClient;
import com.flystonedev.event.mapper.EventEntityMapper;
import com.flystonedev.event.repository.EventEntityRepository;
import com.flystonedev.event.repository.EventTypeRepository;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class EventEntityServiceTest implements SampleData {

    @InjectMocks
    private EventEntityService eventEntityService;

    @Mock
    private EventEntityRepository eventEntityRepository;

    @Mock
    private EventTypeRepository eventTypeRepository;

    @Mock
    private AbstractClient abstractClient;

    @Mock
    private LocalizationClient localizationClient;



    @Test
    void createEventEntity() {
        //given
        EventEntityDTO expected = getSampleOfEventEntityDTO();
        when(abstractClient.abstractOutResponse(anyInt())).thenReturn(AbstractOutResponse.builder().id(1).abstractTitle("Abstract").build());
        when(localizationClient.localizationOutResponse(anyInt())).thenReturn(LocalizationOutResponse.builder().id(1).room("Room 1").build());
        when(eventTypeRepository.getReferenceById(anyInt())).thenReturn(getSampleOfEventType());
        when(eventEntityRepository.save(any())).thenReturn(getSampleOfEventEntity());
        when(localizationClient.createBookingsDTO(any())).thenReturn(BookingsDTO.builder().id(1).build());
        //when
        EventEntityDTO actual = eventEntityService.createEventEntity(expected);
        //then
        System.out.println(actual);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

        verify(eventEntityRepository, times(1)).save(getSampleOfEventEntity());
        verifyNoMoreInteractions(eventEntityRepository);
    }

    @Test
    void eventEntityDTOList() {
        //given

        //when
        eventEntityService.eventEntityDTOList();
        //then
        verify(eventEntityRepository, times(1)).findAll();
        verifyNoMoreInteractions(eventEntityRepository);
    }

    @Test
    void get() {
        //given
        EventEntityDTO expected = getSampleOfEventEntityDTO();
        when(eventEntityRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getSampleOfEventEntity()));

        //when
        EventEntityDTO actual = eventEntityService.get(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(eventEntityRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(eventEntityRepository);

    }

    @Test
    void update() {

        when(eventEntityRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getSampleOfEventEntity()));

        //given
        EventEntityDTO expected = getSampleOfEventEntityDTO();
        when(abstractClient.abstractOutResponse(anyInt())).thenReturn(AbstractOutResponse.builder().id(1).abstractTitle("Abstract").build());
        when(localizationClient.localizationOutResponse(anyInt())).thenReturn(LocalizationOutResponse.builder().id(1).room("Room 1").build());
        when(eventTypeRepository.getReferenceById(anyInt())).thenReturn(getSampleOfEventType());
        when(eventEntityRepository.save(any())).thenReturn(getSampleOfEventEntity());
        when(localizationClient.getBookingsDTO(anyInt())).thenReturn(getSampleOfBookingsDTOLight());
        //when
        EventEntityDTO actual = eventEntityService.update(expected);
        //then
        System.out.println(actual);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

        verify(eventEntityRepository, times(1)).save(any());
        verify(eventEntityRepository, times(1)).findById(any());

        verifyNoMoreInteractions(eventEntityRepository);



    }

    @Test
    void delete() {
        //given
        //when
        eventEntityService.delete(1);
        //then
        verify(eventEntityRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(eventEntityRepository);
        assertThat(eventEntityRepository.findById(1)).isEqualTo(Optional.empty());
    }

}