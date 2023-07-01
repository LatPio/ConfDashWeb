package com.flystonedev.customer.controller;

import com.flystonedev.customer.DTO.InformationLinksDTO;
import com.flystonedev.customer.DTO.InformationLinksRequest;
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
@RequestMapping("api/v1/user/info_links")
@AllArgsConstructor
public class InformationLinksController {

    private final InformationLinksService informationLinksService;

    @RolesAllowed({"USER"})
    @PostMapping
    public void addInformationLink(@RequestBody InformationLinksRequest informationLinksResponse){
        log.info("New Information Link registration to dataBase {}", informationLinksResponse);
        informationLinksService.addUserLinks(informationLinksResponse);
    }
    @RolesAllowed({"USER"})
    @GetMapping
    public ResponseEntity<InformationLinksDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.getUserLink(id));
    }
    @RolesAllowed({"USER"})
    @GetMapping("/list")
    public ResponseEntity<List<InformationLinksDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.informationLinksUserResponseList());
    }
    @RolesAllowed({"USER"})
    @PutMapping
    public ResponseEntity<InformationLinksDTO> update(@RequestBody InformationLinksDTO informationLinksDTO){
        return ResponseEntity.status(HttpStatus.OK).body(informationLinksService.updateUsersLink(informationLinksDTO));
    }

    @RolesAllowed({"USER"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        informationLinksService.deleteUserLink(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
