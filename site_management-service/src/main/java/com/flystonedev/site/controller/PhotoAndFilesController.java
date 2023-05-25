package com.flystonedev.site.controller;

import com.flystonedev.site.DTO.FileResponse;
import com.flystonedev.site.DTO.FilesDTO;
import com.flystonedev.site.model.Files;
import com.flystonedev.site.service.FilesService;
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
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class PhotoAndFilesController {

    private final FilesService filesService;

    @PostMapping
    public void saveFile(@RequestParam("file")MultipartFile multipartFile){

        try {
            log.info("New file Added {}", multipartFile);
            filesService.saveFile(multipartFile);
        } catch (IOException e) {
            log.error("Error occurred while saving to database! ");
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<FilesDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(filesService.get(id));
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download (@PathVariable Integer id){
        FilesDTO filesDTO = get(id).getBody();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filesDTO.getName() + "\"")
                .body(filesDTO.getData());
    }

    @GetMapping("/list")
    public ResponseEntity<List<FilesDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(filesService.filesDTOList());
    }

    @GetMapping("/downloadList")
    public ResponseEntity<List<FileResponse>> getDownloadList(){
        List<FileResponse> files = filesService.filesDTOList().stream().map(
                filesDTO -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/v1/files/download/")
                            .path(String.valueOf(filesDTO.getId()))
                            .toUriString();
                    return new FileResponse(
                            filesDTO.getId(),
                            filesDTO.getName(),
                            filesDTO.getType(),
                            fileDownloadUri
                    );
                }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);

    }

    @PutMapping
    public ResponseEntity<FilesDTO> update(@RequestParam("file")MultipartFile file,
    @RequestParam Integer id) throws IOException{
        FilesDTO filesDTO = get(id).getBody();

        if(file.getBytes().length !=0){
            filesDTO.setData(file.getBytes());
            filesDTO.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            filesDTO.setType(file.getContentType());
        } else {
            filesDTO.setData(filesService.get(filesDTO.getId()).getData());
        }
        return ResponseEntity.status(HttpStatus.OK).body(filesService.update(filesDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        filesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
