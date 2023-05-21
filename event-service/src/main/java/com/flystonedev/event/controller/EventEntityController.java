package com.flystonedev.event.controller;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.service.EventEntityService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/event")
@AllArgsConstructor
public class EventEntityController {

    private final EventEntityService eventEntityService;
    @PostMapping
    public void registerEvent(@RequestBody EventEntityDTO eventEntityDTO){
        log.info("New Event registration {}", eventEntityDTO);
        eventEntityService.createEventEntity(eventEntityDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EventEntityDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.eventEntityDTOList());
    }

    @GetMapping
    public ResponseEntity<EventEntityDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.get(id));
    }

    @PutMapping
    public ResponseEntity<EventEntityDTO> update(@RequestBody EventEntityDTO eventEntityDTO){
        return  ResponseEntity.status(HttpStatus.OK).body(eventEntityService.update(eventEntityDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        eventEntityService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
