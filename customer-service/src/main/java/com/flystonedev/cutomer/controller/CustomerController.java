package com.flystonedev.cutomer.controller;


import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.DTO.CustomerRegistrationRequest;
import com.flystonedev.cutomer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("New Customer Registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listOfAllCustomers());
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(customerDTO));
    }


    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //todo admin access
    //todo customer access
    //todo keycloak user Storage SPI implementation
    //todo adding photo
}
