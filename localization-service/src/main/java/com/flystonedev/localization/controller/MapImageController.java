package com.flystonedev.localization.controller;

import com.flystonedev.localization.DTO.MapImageDTO;
import com.flystonedev.localization.DTO.MapImageRequest;
import com.flystonedev.localization.DTO.MapImageResponse;
import com.flystonedev.localization.DTO.MapImageWithRoomsDTO;
import com.flystonedev.localization.service.MapImageService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/mapImage")
@AllArgsConstructor
public class MapImageController {

    private final MapImageService mapImageService;
    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<MapImageDTO> saveFile(
            @RequestPart("file") MultipartFile file,
            @RequestPart("data") MapImageRequest mapImageRequest){
        try{
            log.info("File saved!");
            return ResponseEntity.status(HttpStatus.OK).body(mapImageService.saveMapImage(file, mapImageRequest));
        } catch (IOException e) {
            log.error("Error occurred while saving to database! ");
            throw new RuntimeException(e);
        }
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping
    public ResponseEntity<MapImageDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(mapImageService.getMapImageDTO(id));
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/rooms")
    public ResponseEntity<MapImageWithRoomsDTO> getWithRooms(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(mapImageService.getMapImageWithRoomsDTO(id));
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<MapImageDTO>> getList(){
        return ResponseEntity.status(HttpStatus.OK).body(mapImageService.mapImageDTOList());
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/simple-list")
    public ResponseEntity<List<MapImageResponse>> getSimpleList(){
        return ResponseEntity.status(HttpStatus.OK).body(mapImageService.mapImageDTOSimpleList());
    }


    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<MapImageDTO> update(
            @RequestPart(value = "file",required = false)MultipartFile file,
            @RequestPart("data") MapImageDTO mapImageDTO) {

        try{
            log.info("File updated!");
            return ResponseEntity.status(HttpStatus.OK).body(mapImageService.updateMapImage(file, mapImageDTO));
        } catch (IOException e) {
            log.error("Error occurred while saving to database! ");
            throw new RuntimeException(e);
        }
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){

        mapImageService.deleteMApImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
