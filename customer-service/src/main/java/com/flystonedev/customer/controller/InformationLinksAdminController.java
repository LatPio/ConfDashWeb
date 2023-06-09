package com.flystonedev.customer.controller;

import com.flystonedev.customer.DTO.InformationLinksAdminDTO;
import com.flystonedev.customer.DTO.InformationLinksAdminRequest;
import com.flystonedev.customer.DTO.InformationLinksDTO;
import com.flystonedev.customer.service.InformationLinksService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/admin/info_links")
@AllArgsConstructor
public class InformationLinksAdminController {

    private final InformationLinksService informationLinksService;

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public void addInformationLink(@RequestBody InformationLinksAdminRequest informationLinksAdminRequest){
        log.info("New Information Link registration to dataBase {}", informationLinksAdminRequest);
        informationLinksService.addAdminLinks(informationLinksAdminRequest);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<InformationLinksAdminDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.getAdminLink(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<InformationLinksDTO>> informationLinksAdminResponseList(){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.informationLinksAdminResponseList());
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<InformationLinksAdminDTO> update(@RequestBody InformationLinksAdminDTO informationLinksAdminDTO){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.updateLinkAdmin(informationLinksAdminDTO));
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Integer id){
        informationLinksService.deleteAdminLink(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
