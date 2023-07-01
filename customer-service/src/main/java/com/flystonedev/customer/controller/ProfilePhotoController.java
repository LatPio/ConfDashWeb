package com.flystonedev.customer.controller;


import com.flystonedev.customer.DTO.CustomerDTO;
import com.flystonedev.customer.DTO.ProfilePhotoDTO;
import com.flystonedev.customer.service.CustomerService;
import com.flystonedev.customer.service.ProfilePhotoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/user/profile_photo")
@AllArgsConstructor
public class ProfilePhotoController {
    private final ProfilePhotoService profilePhotoService;
    private final CustomerService customerService;

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping
    public void savePhoto(@RequestParam("file")MultipartFile file, @RequestParam("id") Integer id){
        try {
            profilePhotoService.savePhoto(file,id);
            log.info("Photo saved!");
        } catch (IOException e) {
            log.error("Error occurred while saving to database!");
            throw new RuntimeException(e);
        }
    }

    @RolesAllowed({"USER"})
    @GetMapping
    public ResponseEntity<ProfilePhotoDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.getUserPhoto(id));
    }


    @RolesAllowed({"USER"})
    @PutMapping
    public ResponseEntity<ProfilePhotoDTO> update(@RequestParam("file")MultipartFile file,
                                                  @RequestParam Integer id )
            throws IOException {
        ProfilePhotoDTO profilePhotoDTO = get(id).getBody();
        if(file.getBytes().length !=0){
            profilePhotoDTO.setData(file.getBytes());
            profilePhotoDTO.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            profilePhotoDTO.setType(file.getContentType());
        } else {
            profilePhotoDTO.setData(profilePhotoService.getUserPhoto(profilePhotoDTO.getId()).getData());
        }
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.updateUser(profilePhotoDTO));
    }
    @RolesAllowed({"USER"})
    @DeleteMapping
    @Transactional
    public ResponseEntity delete(@RequestParam Integer id){
        CustomerDTO customerDTO = customerService.getUser(id);
        customerDTO.setPhoto(null);
        customerService.updateUser(customerDTO);
        profilePhotoService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //todo modifiable only by user owner
}
