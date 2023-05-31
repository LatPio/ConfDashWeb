package com.flystonedev.localization.controller;

import com.flystonedev.localization.DTO.BookingRequest;
import com.flystonedev.localization.DTO.BookingsDTO;
import com.flystonedev.localization.service.BookingsService;
import com.sun.source.tree.LambdaExpressionTree;
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


    @PostMapping
    public BookingsDTO createBooking(@RequestBody BookingRequest bookingRequest){
        log.info("Place booked successful {}", bookingRequest);
        BookingsDTO saved = bookingsService.createBooking(bookingRequest);
        return saved;
    }
    @GetMapping("/list")
    public ResponseEntity<List<BookingsDTO>> bookingsDTOList(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.bookingsDTOList());

    }

    @GetMapping
    public ResponseEntity<BookingsDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.get(id));
    }

    @PutMapping
    public ResponseEntity<BookingsDTO> update(@RequestBody BookingsDTO bookingsDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookingsService.update(bookingsDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        bookingsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
