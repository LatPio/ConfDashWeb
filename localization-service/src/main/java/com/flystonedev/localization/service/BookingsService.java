package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.BookingsDTOLight;
import com.flystonedev.localization.exeption.EntityNotFoundException;
import com.flystonedev.localization.mapper.BookingMapper;
import com.flystonedev.localization.model.Bookings;
import com.flystonedev.localization.model.Localization;
import com.flystonedev.localization.repository.BookingsRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingsService {

    private final BookingsRepository bookingsRepository;
    private final BookingMapper bookingMapper = Mappers.getMapper(BookingMapper.class);

    public BookingsDTO createBooking(BookingRequest bookingRequest){
        Bookings bookings = Bookings.builder()
                .eventIDData(bookingRequest.getEventIDData())
                .locationConflict(bookingRequest.isLocationConflict())
                .timeConflict(bookingRequest.isTimeConflict())
                .dateStart(bookingRequest.getDateStart())
                .dateEnd(bookingRequest.getDateStart().plusMinutes(bookingRequest.getEventTime()).minusSeconds(1L))
                .localization(Localization.builder().id(bookingRequest.getLocalization().getId()).build())
                .build();
        Bookings saved = bookingsRepository.save(bookings);
        return bookingMapper.map(saved);
    }

    public List<BookingsDTOLight> bookingsDTOList(){
        List<Bookings> bookingsList = bookingsRepository.findAll();
        return bookingsList.stream().map(bookingMapper::mapLight).collect(Collectors.toList());
    }

    public BookingsDTO get(Integer id){
        return bookingsRepository.findById(id).map(bookingMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public BookingsDTO update(BookingsDTO bookingsDTO){
        BookingsDTO exist = get(bookingsDTO.getId());
        if (exist == null) {
            return null;
        }
        Bookings updated = bookingsRepository.save(bookingMapper.map(bookingsDTO));
        return bookingMapper.map(updated);
    }

    public void delete(Integer id){
        bookingsRepository.deleteById(id);
    }


}
