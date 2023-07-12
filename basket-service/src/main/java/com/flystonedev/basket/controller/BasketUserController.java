package com.flystonedev.basket.controller;

import com.flystonedev.basket.DTO.BasketDTO;
import com.flystonedev.basket.service.BasketService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user/basket")
public class BasketUserController {

    private final BasketService basketService;

    @RolesAllowed({"USER"})
    @PostMapping
    public ResponseEntity<BasketDTO> addAbstract(@RequestBody BasketDTO abstractDTO){
        log.info("New Booking added {}", abstractDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.bookUSerEvent(abstractDTO));
    }

    @RolesAllowed({"USER"})
    @GetMapping
    public ResponseEntity<BasketDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(basketService.getBooking(id));
    }

    @RolesAllowed({"USER"})
    @GetMapping("/list")
    public ResponseEntity<List<BasketDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(basketService.basketUserListOwnedItems());
    }

    @RolesAllowed({"USER"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        basketService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
