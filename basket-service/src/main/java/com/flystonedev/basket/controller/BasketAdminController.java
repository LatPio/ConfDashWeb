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
@RequestMapping("api/v1/admin/basket")
public class BasketAdminController {

    private final BasketService basketService;

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<BasketDTO> addAbstract(@RequestBody BasketDTO basketDTO){
        log.info("New Booking added {}", basketDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.bookAdminEvent(basketDTO));
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<BasketDTO> get(@RequestParam Integer id, @RequestParam String authId){
        return ResponseEntity.status(HttpStatus.OK).body(basketService.getAdminBooking(id, authId));
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<BasketDTO>> list( @RequestParam String authId){
        return ResponseEntity.status(HttpStatus.OK).body(basketService.basketAdminListOwnedItems(authId));
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id, @RequestParam String authId){
        basketService.deleteAdminBooking(id, authId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
