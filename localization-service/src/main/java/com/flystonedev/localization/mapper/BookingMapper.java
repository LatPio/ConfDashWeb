package com.flystonedev.localization.mapper;

import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.BookingsDTOLight;
import com.flystonedev.localization.model.Bookings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingsDTO map(Bookings bookings);
    Bookings map(BookingsDTO bookingsDTO);

    BookingsDTOLight mapLight(Bookings bookings);
}
