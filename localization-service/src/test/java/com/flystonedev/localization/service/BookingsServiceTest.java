package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.SampleData;
import com.flystonedev.localization.mapper.BookingMapper;
import com.flystonedev.localization.model.Bookings;
import com.flystonedev.localization.repository.BookingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingsServiceTest implements SampleData {

    @InjectMocks
    private BookingsService bookingsService;

    @Mock
    private BookingsRepository bookingsRepository;

    private final BookingMapper bookingMapper = Mappers.getMapper(BookingMapper.class);


    @Test
    void createBooking() {
        //given
        BookingsDTO excepted = getSampleOfBookingDTO();
        BookingRequest request = getSampleOfBookingRequest();
        excepted.setId(null);

        //when
        bookingsService.createBooking(request);
        //then

        ArgumentCaptor<Bookings> argumentCaptor = ArgumentCaptor.forClass(Bookings.class);
        verify(bookingsRepository).save(argumentCaptor.capture());

        Bookings value = argumentCaptor.getValue();
        assertThat(value.getDateStart()).isEqualTo(request.getDateStart());
        assertThat(value.getEventIDData()).isEqualTo(request.getEventIDData());
        assertThat(value.isLocationConflict()).isEqualTo(request.isLocationConflict());

    }

    @Test
    void bookingsDTOList() {
        //given

        //when
        bookingsService.bookingsDTOList();
        //then
        verify(bookingsRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookingsRepository);
    }

    @Test
    void get() {
        //given
        BookingsDTO excepted = getSampleOfBookingDTO();
        when(bookingsRepository.findById(anyInt())).thenReturn(Optional.ofNullable(bookingMapper.map(excepted)));
        //then
        BookingsDTO actual = bookingsService.get(excepted.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(bookingsRepository, times(1)).findById(excepted.getId());
        verifyNoMoreInteractions(bookingsRepository);

    }

    @Test
    void update() {
        //given
        BookingsDTO excepted = getSampleOfBookingDTO();
        when(bookingsRepository.findById(anyInt())).thenReturn(Optional.ofNullable(bookingMapper.map(excepted)));
        when(bookingsRepository.save(any(Bookings.class))).thenReturn(bookingMapper.map(excepted));
        //when
        BookingsDTO actual = bookingsService.update(excepted);
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(excepted);
        verify(bookingsRepository, times(1)).save(any(Bookings.class));
        verifyNoMoreInteractions(bookingsRepository);



    }

    @Test
    void delete() {
        //given
        doNothing().when(bookingsRepository).deleteById(anyInt());
        //when
        //then
        bookingsService.delete(1);
        verify(bookingsRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(bookingsRepository);
    }


}