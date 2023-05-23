package com.flystonedev.cutomer.controller;


import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.DTO.ProfilePhotoDTO;
import com.flystonedev.cutomer.DTO.ProfilePhotoResponse;
import com.flystonedev.cutomer.service.CustomerService;
import com.flystonedev.cutomer.service.ProfilePhotoService;
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
@RequestMapping("api/v1/profile_photo")
@AllArgsConstructor
public class ProfilePhotoController {
    private final ProfilePhotoService profilePhotoService;
    private final CustomerService customerService;

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

    @GetMapping
    public ResponseEntity<ProfilePhotoDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.get(id));
    }
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        ProfilePhotoDTO profilePhotoDTO = get(id).getBody();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePhotoDTO.getName() + "\"")
                .body(profilePhotoDTO.getData());
    }

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
            profilePhotoDTO.setData(profilePhotoService.get(profilePhotoDTO.getId()).getData());
        }
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.update(profilePhotoDTO));
    }
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        CustomerDTO customerDTO = customerService.get(id);
        customerDTO.setPhoto(null);
        customerService.update(customerDTO);
        profilePhotoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
