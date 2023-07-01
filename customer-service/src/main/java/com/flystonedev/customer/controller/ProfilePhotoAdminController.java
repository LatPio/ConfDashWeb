package com.flystonedev.customer.controller;


import com.flystonedev.customer.DTO.ProfilePhotoDTO;
import com.flystonedev.customer.DTO.ProfilePhotoResponse;
import com.flystonedev.customer.service.CustomerService;
import com.flystonedev.customer.service.ProfilePhotoService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/admin/profile_photo")
@AllArgsConstructor
public class ProfilePhotoAdminController {
    private final ProfilePhotoService profilePhotoService;
    private final CustomerService customerService;

    @RolesAllowed({"ADMIN"})
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

    @RolesAllowed({"ADMIN"})
    @GetMapping()
    public ResponseEntity<ProfilePhotoDTO> getAdmin (@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.getAdmin(id));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        ProfilePhotoDTO profilePhotoDTO = getAdmin(id).getBody();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePhotoDTO.getName() + "\"")
                .body(profilePhotoDTO.getData());
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<ProfilePhotoResponse>> getListFiles(){
        List<ProfilePhotoResponse> files = profilePhotoService.profilePhotoDTOList().stream().map(
                profilePhotoDTO -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/v1/profile_photo/file/")
                            .path(String.valueOf(profilePhotoDTO.getId()))
                            .toUriString();
                    return new ProfilePhotoResponse(
                            profilePhotoDTO.getId(), profilePhotoDTO.getName(), profilePhotoDTO.getType(), fileDownloadUri
                            );
                }
        ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<ProfilePhotoDTO> update(@RequestParam("file")MultipartFile file,
                                                  @RequestParam Integer id )
            throws IOException {
        ProfilePhotoDTO profilePhotoDTO = getAdmin(id).getBody();
        if(file.getBytes().length !=0){
            profilePhotoDTO.setData(file.getBytes());
            profilePhotoDTO.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            profilePhotoDTO.setType(file.getContentType());
        } else {
            profilePhotoDTO.setData(profilePhotoService.getUserPhoto(profilePhotoDTO.getId()).getData());
        }
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.updateAdmin(profilePhotoDTO));
    }
    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){

        profilePhotoService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
