package com.flystonedev.cutomer.controller;

import com.flystonedev.cutomer.DTO.InformationLinksDTO;
import com.flystonedev.cutomer.service.InformationLinksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/info_links")
@AllArgsConstructor
public class InformationLinksController {

    private final InformationLinksService informationLinksService;


    @PostMapping
    public void addInformationLink(@RequestBody InformationLinksDTO informationLinksResponse){
        log.info("New Department registration to dataBase {}", informationLinksResponse);
        informationLinksService.addLinks(informationLinksResponse);
    }

    @GetMapping
    public ResponseEntity<InformationLinksDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<InformationLinksDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.informationLinksResponseList());
    }
    @PutMapping
    public ResponseEntity<InformationLinksDTO> update(@RequestBody InformationLinksDTO informationLinksDTO){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.update(informationLinksDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        informationLinksService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
