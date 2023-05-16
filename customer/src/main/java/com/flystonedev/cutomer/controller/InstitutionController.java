package com.flystonedev.cutomer.controller;

import com.flystonedev.cutomer.DTO.InstitutionDTO;
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
    public void addInstitution(@RequestBody InstitutionDTO institutionRecord){
        log.info("New Customer Registration {}", institutionRecord);
        institutionService.addInstitution(institutionRecord);
    }

    @GetMapping
    public ResponseEntity<InstitutionDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<InstitutionDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.institutionRecordList());
    }

    @PutMapping
    public ResponseEntity<InstitutionDTO> update(@RequestBody InstitutionDTO institutionDTO){
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.update(institutionDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        institutionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
