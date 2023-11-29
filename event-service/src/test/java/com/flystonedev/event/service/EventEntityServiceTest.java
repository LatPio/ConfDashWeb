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

    private final EventEntityMapper eventEntityMapper = Mappers.getMapper(EventEntityMapper.class);


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
        eventEntityService.createEventEntity(expected);
        //then
        verify(eventEntityRepository, times(1)).save(getSampleOfEventEntity());
        verifyNoMoreInteractions(eventEntityRepository);
    }

    @Test
    void eventEntityDTOList() {
    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void abstractOutResponse() {

    }

    @Test
    void localizationOutResponse() {
    }

    @Test
    void createBookingsDTO() {
    }
}