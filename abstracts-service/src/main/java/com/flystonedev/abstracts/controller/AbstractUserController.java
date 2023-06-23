package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.service.AbstractService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/user/abstracts")
@AllArgsConstructor
public class AbstractUserController {
    private final AbstractService abstractService;

    @RolesAllowed({"USER"})
    @PostMapping
    public ResponseEntity<AbstractDTO> addAbstract(@RequestBody AbstractDTO abstractDTO){
        log.info("New Abstract added {}", abstractDTO);
        ;
        return ResponseEntity.status(HttpStatus.CREATED).body(abstractService.createUserAbstract(abstractDTO));
    }
    @RolesAllowed({"USER"})
    @GetMapping
    public ResponseEntity<AbstractDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.getUsersAbstract(id));
    }

    @RolesAllowed({"USER"})
    @GetMapping("/list")
    public ResponseEntity<List<AbstractDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.abstractUsersDTOList());
    }

    @RolesAllowed({"USER"})
    @GetMapping("/listAccepted")
    public ResponseEntity<List<AbstractDTO>> listAccepted(){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.abstractUserAcceptedDTOList());
    }
    @RolesAllowed({"USER"})
    @PutMapping
    public ResponseEntity<AbstractDTO> update(@RequestBody AbstractDTO abstractDTO){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.updateUsersAbstract(abstractDTO));
    }

    @RolesAllowed({"USER"})
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer id){
        abstractService.deleteUsersAbstract(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
