package com.flystonedev.cutomer.controller;


import com.flystonedev.cutomer.DTO.CustomerAdminDTO;
import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.DTO.CustomerRegistrationRequest;
import com.flystonedev.cutomer.service.CustomerService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1/admin/customer")
@AllArgsConstructor
public class CustomerAdminController {

    private final CustomerService customerService;

    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<CustomerAdminDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAdmin(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<CustomerAdminDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listOfAllCustomersForAdmin());
    }
    @RolesAllowed({"USER", "ADMIN"})
    @PutMapping
    public ResponseEntity<CustomerAdminDTO> update(@RequestBody CustomerAdminDTO customerAdminDTO){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateAdmin(customerAdminDTO));
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully From Local and Security Database");
    }


}
