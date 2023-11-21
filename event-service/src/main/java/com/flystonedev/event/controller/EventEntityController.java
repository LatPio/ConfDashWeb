package com.flystonedev.event.controller;

import com.flystonedev.event.DTO.EventEntityDTO;
import com.flystonedev.event.DTO.StatisticResponse;
import com.flystonedev.event.service.EventEntityService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.annotation.security.RolesAllowed;
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

    @RolesAllowed({"ADMIN"})
    @PostMapping
    @CircuitBreaker(name = "event-cals", fallbackMethod = "fallbackRegisterEvent")
    @TimeLimiter(name = "event-cals")
    @Retry(name = "abstract")
    public CompletableFuture<String> registerEvent(@RequestBody EventEntityDTO eventEntityDTO){
        eventEntityService.createEventEntity(eventEntityDTO);
        return CompletableFuture.supplyAsync(()->"Event created successfully");
    }
    public CompletableFuture<String> fallbackRegisterEvent(@RequestBody EventEntityDTO eventEntityDTO, Throwable e){

        log.info("Error cause: {"+ e.getMessage().split(":")[1] +"}", e.getMessage().split(":")[1]);
        return CompletableFuture.supplyAsync(()->"Something gone wrong, probably: "+ e.getMessage().split(":")[1]+", or some features may be off-line try later.");
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<EventEntityDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.eventEntityDTOList());
    }

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping("/user-list")
    public ResponseEntity<List<EventEntityDTO>> userList(@RequestBody List<Integer> listOfEvents){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.eventEntityDTOuserList(listOfEvents));
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/list/room")
    public ResponseEntity<List<EventEntityDTO>> listByRoom(@RequestParam String id){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.eventEntityDTOListByLocalization(id));
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<EventEntityDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.get(id));
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<EventEntityDTO> update(@RequestBody EventEntityDTO eventEntityDTO){
        return  ResponseEntity.status(HttpStatus.OK).body(eventEntityService.update(eventEntityDTO));
    }
    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        eventEntityService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/stats")
    public ResponseEntity<StatisticResponse> getStats(){
        return ResponseEntity.status(HttpStatus.OK).body(eventEntityService.statistic());
    }

//todo: show event field for repository to filter events for user to get, endpoint for activation these events single as well from list

}
