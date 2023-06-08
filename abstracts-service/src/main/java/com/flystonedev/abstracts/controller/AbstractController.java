package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AbstractBlockDTO;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.service.AbstractService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/abstracts")
@AllArgsConstructor
public class AbstractController {
    private final AbstractService abstractService;

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping
    public void addAbstract(@RequestBody AbstractDTO abstractDTO){
        log.info("New Abstract added {}", abstractDTO);
        abstractService.createAbstract(abstractDTO);
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<AbstractDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.get(id));
        //todo modifiable/accessible only by user owner
        //todo modifiable/accessible aftrer blocked it can be viewed by everyone
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/simple")
    public ResponseEntity<AbstractOutResponse> getSimple(@RequestParam Integer id){
        //todo modifiable/accessible only by user owner
        //todo modifiable/accessible aftrer blocked it can be viewed by everyone
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.getSimple(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<AbstractDTO>> list(){
        //todo modifiable/accessible only by user owner
        //todo modifiable/accessible aftrer blocked it can be viewed by everyone
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.abstractDTOList());
    }
    @RolesAllowed({"USER", "ADMIN"})
    @PutMapping
    public ResponseEntity<AbstractDTO> update(@RequestBody AbstractDTO abstractDTO){
        //todo modifiable/accessible only by user owner
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.update(abstractDTO));
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("/block")
    public ResponseEntity<AbstractBlockDTO> updateBlockEdit(@RequestBody AbstractBlockDTO abstractBlockDTO){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.updateBlockEdit(abstractBlockDTO));
    }
    @RolesAllowed({"USER", "ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        abstractService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
        //todo block delete for user after abstract is market as accept: true
    }
}
