package com.flystonedev.site.controller;

import com.flystonedev.site.DTO.SiteDTO;
import com.flystonedev.site.service.SiteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/site")
@AllArgsConstructor
public class SiteController {

    private SiteService siteService;


    @PostMapping
    public void registerSite(@RequestBody SiteDTO siteDTO){
        log.info("New Site saved {}", siteDTO);
        siteService.createSite(siteDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<SiteDTO>> listResponseEntity(){
        return ResponseEntity.status(HttpStatus.OK).body(siteService.siteDTOS());
    }

    @GetMapping
    public ResponseEntity<SiteDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(siteService.get(id));
    }
    @PutMapping
    public ResponseEntity<SiteDTO> update(@RequestBody SiteDTO siteDTO){
        return ResponseEntity.status(HttpStatus.OK).body(siteService.update(siteDTO));
    }
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        siteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
