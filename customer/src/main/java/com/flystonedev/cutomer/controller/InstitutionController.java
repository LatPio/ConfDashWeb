package com.flystonedev.cutomer.controller;

import com.flystonedev.cutomer.records.CustomerRegistrationRequest;
import com.flystonedev.cutomer.records.CustomerResponse;
import com.flystonedev.cutomer.records.InstitutionRecord;
import com.flystonedev.cutomer.service.InstitutionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/institution")
@AllArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;



    @PostMapping
    public void addInstitution(@RequestBody InstitutionRecord institutionRecord){
        log.info("New Customer Registration {}", institutionRecord);
        institutionService.addInstitution(institutionRecord);
    }

    @GetMapping
    public ResponseEntity<InstitutionRecord> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<InstitutionRecord>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.institutionRecordList());
    }

    @PutMapping
    public ResponseEntity<InstitutionRecord> update(@RequestBody InstitutionRecord institutionRecord){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.update(institutionRecord));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        institutionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
