package com.flystonedev.localization;

import com.flystonedev.localization.DTO.*;
import com.flystonedev.localization.model.Localization;
import com.flystonedev.localization.model.MapImage;
import io.restassured.internal.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;

public interface SampleData {

    default BookingsDTO getSampleOfBookingDTO(){
        BookingsDTO bookingsDTO = BookingsDTO.builder()
                .id(1)
                .eventIDData(1)
                .locationConflict(false)
                .timeConflict(false)
                .dateStart(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toLocalDateTime())
                .dateEnd( LocalDate.of(2022,5,1).atTime(10,55,44).atZone(ZoneOffset.UTC).toLocalDateTime() )
                .localization(LocalizationDTO.builder().id(1).build())
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
                .eventTime(20)
                .build();
        return bookingRequest;
    }

    default LocalizationDTO getSampleOfLocalizationDTO(){
        LocalizationDTO localizationDTO = LocalizationDTO.builder()
                .id(1)
                .room("Room 1")
                .coordinateX(34)
                .coordinateY(67)
                .mapImage(getSampleOfMapImageDTO())
                .build();
        return localizationDTO;
    }
    default Localization getSampleOfLocalization(){
        Localization localization = Localization.builder()
                .id(1)
                .room("Room 1")
                .coordinateX(34)
                .coordinateY(67)
                .mapImage(getSampleOfMapImage())
                .build();
        return localization;
    }

    default LocalizationOutResponse getSampleLocalizationOutResponse(){
        LocalizationOutResponse localizationOutResponse = LocalizationOutResponse.builder()
                .id(1).room("Room 1").build();
        return localizationOutResponse;
    }

    default MapImage getSampleOfMapImage(){
        MapImage mapImage = MapImage.builder()
                .name("Map")
                .fileName("map")
                .data("Hello, World!".getBytes())
                .localization(null)
                .build();
        return mapImage;

    }
    default MapImageDTO getSampleOfMapImageDTO(){
        MapImageDTO mapImageDTO = MapImageDTO.builder()
                .name("map")
                .fileName("Hello.txt")
                .data("Hello, World!".getBytes())
                .build();
        return mapImageDTO;

    }

    default MultipartFile getSampleMultipart(){
        return new MockMultipartFile(
                "file",
                "Hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
    }

    default MultipartFile getSampleMultipartImage() throws IOException {
        File file = new File("src/test/resources/kot.jpg");
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile(
                "file",
                "kot.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                IOUtils.toByteArray(input)
        );
    }


    default MapImageRequest getSampleOfMapImageRequest(){
        MapImageRequest mapImageRequest = MapImageRequest.builder()
                .name("map")
                .build();
        return mapImageRequest;
    }

}
