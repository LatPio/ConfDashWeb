package com.flystonedev.customer.controller;


import com.flystonedev.customer.DTO.CustomerCardDTO;
import com.flystonedev.customer.DTO.CustomerDTO;
import com.flystonedev.customer.DTO.CustomerIdDTO;
import com.flystonedev.customer.DTO.CustomerRegistrationRequest;
import com.flystonedev.customer.service.CustomerService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/user/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/new")
    public ResponseEntity registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) throws Exception {
        log.info("New Customer Registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body("User Created Successfully");
    }

    @RolesAllowed({"USER"})
    @RequestMapping(method = RequestMethod.GET ,params = {"id"})
    public ResponseEntity<CustomerDTO> getUser(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getUser(id));
    }

    @RolesAllowed({"USER"})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CustomerDTO> getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getUserByAuthId());
    }

    @RolesAllowed({"USER"})
    @GetMapping("/simple")
    public ResponseEntity<CustomerIdDTO> getSimpleUserData(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getUserSimpleData());
    }

    @RolesAllowed({"USER"})
    @GetMapping("/card")
    public ResponseEntity<CustomerCardDTO> getCard(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getUserSimple(id));
    }
    @RolesAllowed({"USER"})
    @GetMapping("/list")
    public ResponseEntity<List<CustomerCardDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listOfAllCustomersCards());
    }
    @RolesAllowed({"USER"})
    @PutMapping
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateUser(customerDTO));
    }
}
