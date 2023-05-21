package com.flystonedev.site.controller;

import com.flystonedev.site.DTO.PhotosAndFilesDTO;
import com.flystonedev.site.service.PhotosAndFilesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class PhotoAndFilesController {

    private final PhotosAndFilesService photosAndFilesService;

    @PostMapping
    public void registerFile(@RequestBody PhotosAndFilesDTO photosAndFilesDTO){
        log.info("New file Added {}" , photosAndFilesDTO);
        photosAndFilesService.saveFile(photosAndFilesDTO);
    }

    @GetMapping
    public ResponseEntity<PhotosAndFilesDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(photosAndFilesService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PhotosAndFilesDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(photosAndFilesService.photosAndFilesDTOList());
    }

    @PutMapping
    public ResponseEntity<PhotosAndFilesDTO> update(@RequestBody PhotosAndFilesDTO photosAndFilesDTO){
        return ResponseEntity.status(HttpStatus.OK).body(photosAndFilesService.update(photosAndFilesDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        photosAndFilesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
