package com.flystonedev.cutomer.controller;

import com.flystonedev.cutomer.records.DepartmentResponse;
import com.flystonedev.cutomer.records.InformationLinksResponse;
import com.flystonedev.cutomer.repository.InformationLinksRepository;
import com.flystonedev.cutomer.service.DepartmentService;
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
    public void addInformationLink(@RequestBody InformationLinksResponse informationLinksResponse){
        log.info("New Department registration to dataBase {}", informationLinksResponse);
        informationLinksService.addLinks(informationLinksResponse);
    }

    @GetMapping
    public ResponseEntity<InformationLinksResponse> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<InformationLinksResponse>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.informationLinksResponseList());
    }
    @PutMapping
    public ResponseEntity<InformationLinksResponse> update(@RequestBody InformationLinksResponse informationLinksResponse){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.update(informationLinksResponse));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        informationLinksService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
