package com.flystonedev.event.controller;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.EventTypeDTO;
import com.flystonedev.event.model.EventType;
import com.flystonedev.event.service.EventTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/event-type")
@AllArgsConstructor
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @PostMapping
    public void registerEventType(@RequestBody EventTypeDTO eventTypeDTO){
        log.info("New Event Type registered {}", eventTypeDTO);
        eventTypeService.createEventType(eventTypeDTO);
    }


    @GetMapping("/list")
    public ResponseEntity<List<EventTypeDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(eventTypeService.eventTypeDTOList());
    }

    @GetMapping
    public ResponseEntity<EventTypeDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(eventTypeService.get(id));
    }

    @PutMapping
    public ResponseEntity<EventTypeDTO> update(@RequestBody EventTypeDTO eventTypeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(eventTypeService.update(eventTypeDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        eventTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
