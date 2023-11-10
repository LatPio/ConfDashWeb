package com.flystonedev.localization;

import com.flystonedev.localization.DTO.*;
import com.flystonedev.localization.model.Localization;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;

public interface SampleData {

    default BookingsDTO getSampleOfBookingDTO(){
        BookingsDTO bookingsDTO = BookingsDTO.builder()
                .id(1)
                .eventIDData(1)
                .locationConflict(false)
                .timeConflict(false)
                .dateStart(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toLocalDateTime())
                .dateEnd( LocalDate.of(2022,5,1).atTime(10,55,44).atZone(ZoneOffset.UTC).toLocalDateTime() )
                .localization(LocalizationWithOutMapDTO.builder().id(1).build())
                .build();
        return bookingsDTO;
    }

    default BookingRequest getSampleOfBookingRequest(){
        BookingRequest bookingRequest = BookingRequest.builder()
                .eventIDData(1)
                .locationConflict(false)
                .timeConflict(false)
                .dateStart(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toLocalDateTime())
                .localization(LocalizationDTO.builder().id(1).build())
                .eventTime(Duration.ofMinutes(20))
                .build();
        return bookingRequest;
    }

    default LocalizationDTO getSampleOfLocalizationDTO(){
        LocalizationDTO localizationDTO = LocalizationDTO.builder()
                .id(1)
                .room("Room 1")
                .coordinateX(34)
                .coordinateY(67)
                .mapImage(null)
                .build();
        return localizationDTO;
    }
    default Localization getSampleOfLocalization(){
        Localization localization = Localization.builder()
                .id(1)
                .room("Room 1")
                .coordinateX(34)
                .coordinateY(67)
                .mapImage(null)
                .build();
        return localization;
    }

    default LocalizationOutResponse getSampleLocalizationOutResponse(){
        LocalizationOutResponse localizationOutResponse = LocalizationOutResponse.builder()
                .id(1).room("Room 1").build();
        return localizationOutResponse;
    }


}
