package com.flystonedev.localization.controller;

import com.flystonedev.localization.DTO.LocalizationDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import com.flystonedev.localization.service.LocalizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/localization")
@AllArgsConstructor
public class LocalizationController {

    private final LocalizationService localizationService;

    @PostMapping
    public void addLocalization(@RequestBody LocalizationDTO localizationDTO){
        log.info("Created Localization {}", localizationDTO);
        localizationService.createLocalization(localizationDTO);
    }

    @GetMapping
    public ResponseEntity<LocalizationDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(localizationService.get(id));
    }

    @GetMapping("/simple")
    public ResponseEntity<LocalizationOutResponse> getSimple(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(localizationService.getSimple(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<LocalizationDTO>> listResponseEntity(){
        return ResponseEntity.status(HttpStatus.OK).body(localizationService.localizationDTOList());
    }
    @PutMapping
    public ResponseEntity<LocalizationDTO> update(@RequestBody LocalizationDTO localizationDTO){
        return ResponseEntity.status(HttpStatus.OK).body(localizationService.update(localizationDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        localizationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
