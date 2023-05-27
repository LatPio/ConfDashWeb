package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.service.AbstractService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/abstracts")
@AllArgsConstructor
public class AbstractController {
    private final AbstractService abstractService;

    @PostMapping
    public void addAbstract(@RequestBody AbstractDTO abstractDTO){
        log.info("New Abstract added {}", abstractDTO);
        abstractService.createAbstract(abstractDTO);
    }

    @GetMapping
    public ResponseEntity<AbstractDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.get(id));
    }

    @GetMapping("/simple")
    public ResponseEntity<AbstractOutResponse> getSimple(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.getSimple(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AbstractDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.abstractDTOList());
    }
    @PutMapping
    public ResponseEntity<AbstractDTO> update(@RequestBody AbstractDTO abstractDTO){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.update(abstractDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        abstractService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
