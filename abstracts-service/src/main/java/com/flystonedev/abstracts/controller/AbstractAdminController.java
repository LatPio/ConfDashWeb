package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AbstractBlockDTO;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.config.JwtConverter;
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
@RequestMapping("api/v1/admin/abstracts")
@AllArgsConstructor
public class AbstractAdminController {
    private final AbstractService abstractService;

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public void addAbstract(@RequestBody AbstractDTO abstractDTO){
        log.info("New Abstract added {}", abstractDTO);
        abstractService.createAdminAbstract(abstractDTO);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<AbstractDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.getAbstractByAdmin(id));
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/simple")
    public ResponseEntity<AbstractOutResponse> getSimple(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.getSimple(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<AbstractDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.abstractAdminDTOList());
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<AbstractDTO> updateAdmin(@RequestBody AbstractDTO abstractDTO){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.updateAbstractByAdmin(abstractDTO));
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("/block")
    public ResponseEntity<AbstractBlockDTO> updateBlockEdit(@RequestBody AbstractBlockDTO abstractBlockDTO){
        return ResponseEntity.status(HttpStatus.OK).body(abstractService.updateAbstractAdminBlockEdit(abstractBlockDTO));
    }
    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        abstractService.deleteAdminAbstract(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/principal")
    public ResponseEntity<String> getPrincipal(){
        System.out.println(JwtConverter.getKeycloakJWT().getId().toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
