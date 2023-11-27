package com.flystonedev.customer.controller;

import com.flystonedev.customer.DTO.InvoiceDataDTO;
import com.flystonedev.customer.service.InvoiceDataService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/invoice")
@AllArgsConstructor
public class InvoiceDataController {

    private final InvoiceDataService invoiceDataService;

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping
    public ResponseEntity<InvoiceDataDTO> addDepartment (@RequestBody InvoiceDataDTO invoiceDataDTO){
        log.info("New Department registration to dataBase {}", invoiceDataDTO);
//        invoiceDataService.addDepartment(invoiceDataDTO);
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDataService.addDepartment(invoiceDataDTO));
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<InvoiceDataDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDataService.get(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<InvoiceDataDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDataService.departmentResponseList());
    }
    @RolesAllowed({"ADMIN", "USER"})
    @PutMapping
    public ResponseEntity<InvoiceDataDTO> update(@RequestBody InvoiceDataDTO invoiceDataDTO){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDataService.update(invoiceDataDTO));
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        invoiceDataService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
