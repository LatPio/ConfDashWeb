package com.flystonedev.customer.controller;


import com.flystonedev.customer.DTO.CustomerAdminDTO;
import com.flystonedev.customer.DTO.CustomerCardDTO;
import com.flystonedev.customer.DTO.StatsResponse;
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
@RequestMapping("api/v1/admin/customer")
@AllArgsConstructor
public class CustomerAdminController {

    private final CustomerService customerService;

    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<CustomerAdminDTO> get(@RequestParam(name = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAdmin(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/card")
    public ResponseEntity<CustomerCardDTO> getCard(@RequestParam(name = "authId") String authID){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getUserSimple(authID));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<CustomerAdminDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listOfAllCustomersForAdmin());
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/auth-list")
    public ResponseEntity<List<String>> listOfAuthsId(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.listOfAllAuthIdsCustomersForAdmin());
    }
    @RolesAllowed({"ADMIN"})
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

    @RolesAllowed({"ADMIN"})
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> stats(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.stats());
    }


}
