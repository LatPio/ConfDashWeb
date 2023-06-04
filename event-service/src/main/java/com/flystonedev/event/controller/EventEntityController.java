package com.flystonedev.event.controller;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.service.EventEntityService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("api/v1/event")
@AllArgsConstructor
public class EventEntityController {

    private final EventEntityService eventEntityService;
    @PostMapping
    @CircuitBreaker(name = "event-cals", fallbackMethod = "fallbackRegisterEvent")
    @TimeLimiter(name = "event-cals")
    @Retry(name = "abstract")
    public CompletableFuture<String> registerEvent(@RequestBody EventEntityDTO eventEntityDTO){
        log.info("New Event registration {}", eventEntityDTO);
        eventEntityService.createEventEntity(eventEntityDTO);
        return CompletableFuture.supplyAsync(()->"Event created successfully");
    }
    public CompletableFuture<String> fallbackRegisterEvent(@RequestBody EventEntityDTO eventEntityDTO, Throwable e){

        log.info("Error cause: {"+ e.getMessage().split(":")[1] +"}", e.getMessage().split(":")[1]);
        return CompletableFuture.supplyAsync(()->"Something gone wrong, probably: "+ e.getMessage().split(":")[1]+", or some features may be off-line try later.");
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
