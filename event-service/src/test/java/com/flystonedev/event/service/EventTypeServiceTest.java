package com.flystonedev.event.service;

import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.SampleData;
import com.flystonedev.event.mapper.EventTypeMapper;
import com.flystonedev.event.model.EventType;
import com.flystonedev.event.repository.EventTypeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class EventTypeServiceTest implements SampleData {

    @InjectMocks
    private EventTypeService eventTypeService;

    @Mock
    private EventTypeRepository eventTypeRepository;

    private final EventTypeMapper eventTypeMapper = Mappers.getMapper(EventTypeMapper.class);


    @Test
    void createEventType() {
        //given
        EventTypeDTO expected = getSampleOfEventTypeDTO();
        expected.setId(null);
        //when
        eventTypeService.createEventType(expected);
        //then
        ArgumentCaptor<EventType> eventTypeArgumentCaptor = ArgumentCaptor.forClass(EventType.class);
        verify(eventTypeRepository).save(eventTypeArgumentCaptor.capture());

        EventType eventTypeArgumentCaptorValue = eventTypeArgumentCaptor.getValue();
        assertThat(eventTypeArgumentCaptorValue).isEqualTo(eventTypeMapper.map(expected));
    }

    @Test
    void eventTypeDTOList() {
        //given
        when(eventTypeRepository.findAll()).thenReturn(getSampleOfEventTypeList());
        List<EventTypeDTO> expected = getSampleOfEventTypeDTOList();
        //when
        List<EventTypeDTO> actual = eventTypeService.eventTypeDTOList();
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(eventTypeRepository, times(1)).findAll();
        verifyNoMoreInteractions(eventTypeRepository);
    }

    @Test
    void get() {
        //given
        EventType expected = getSampleOfEventType();
        when(eventTypeRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(expected));
        //when
        EventTypeDTO actual = eventTypeService.get(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(eventTypeRepository, times(1)).findById(any());
        verifyNoMoreInteractions(eventTypeRepository);
    }

    @Test
    void update() {
        //given
        EventTypeDTO expected = getSampleOfEventTypeDTO();
        when(eventTypeRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(getSampleOfEventType()));
        //when
        eventTypeService.update(expected);
        //then
        ArgumentCaptor<EventType> eventTypeArgumentCaptor = ArgumentCaptor.forClass(EventType.class);
        verify(eventTypeRepository).save(eventTypeArgumentCaptor.capture());

        EventType eventTypeArgumentCaptorValue = eventTypeArgumentCaptor.getValue();
        assertThat(eventTypeArgumentCaptorValue).isEqualTo(eventTypeMapper.map(expected));
    }

    @Test
    void delete() {
        //given
        EventTypeDTO expected = getSampleOfEventTypeDTO();

        //when
        eventTypeService.delete(expected.getId());
        //then
        verify(eventTypeRepository, times(1)).deleteById(any());
        verifyNoMoreInteractions(eventTypeRepository);
    }
}