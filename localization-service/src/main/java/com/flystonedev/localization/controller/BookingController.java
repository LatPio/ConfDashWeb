package com.flystonedev.localization.controller;

import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.DTO.BookingsDTOLight;
import com.flystonedev.localization.DTO.BookingsUpdateRequest;
import com.flystonedev.localization.service.BookingsService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/booking")
@AllArgsConstructor
public class BookingController {
    private final BookingsService bookingsService;

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public BookingsDTO createBooking(@RequestBody BookingRequest bookingRequest){
        log.info("Place booked successful {}", bookingRequest);
        BookingsDTO saved = bookingsService.createBooking(bookingRequest);
        return saved;
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<BookingsDTOLight>> bookingsDTOList(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.bookingsDTOList());

    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<BookingsDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.get(id));
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/simple")
    public ResponseEntity<BookingsDTOLight> getSimple(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.getSimple(id));
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("/simple")
    public ResponseEntity<BookingsDTOLight> updateSimple(@RequestBody BookingsUpdateRequest bookingsUpdateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.updateSimple(bookingsUpdateRequest));
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<BookingsDTO> update(@RequestBody BookingsDTO bookingsDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.update(bookingsDTO));
    }
    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        bookingsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
