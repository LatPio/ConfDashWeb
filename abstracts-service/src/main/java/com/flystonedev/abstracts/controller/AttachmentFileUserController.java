package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AttachmentFileDTO;
import com.flystonedev.abstracts.DTO.AttachmentFileRequest;
import com.flystonedev.abstracts.DTO.AttachmentFileResponse;
import com.flystonedev.abstracts.model.FileRole;
import com.flystonedev.abstracts.service.AttachmentFileService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/user/attachment_file")
@AllArgsConstructor
public class AttachmentFileUserController {

    private final AttachmentFileService attachmentFileService;

    @RolesAllowed({"USER"})
    @PostMapping
    public void saveFile(@RequestPart("file")MultipartFile file, @RequestPart("data") AttachmentFileRequest attachmentFileRequest){
        try{
            attachmentFileService.saveUsersFile(file,attachmentFileRequest);
            log.info("File saved!");
        } catch (IOException e) {
            log.error("Error occurred while saving to database! ");
            throw new RuntimeException(e);
        }
    }

    @RolesAllowed({"USER"})
    @GetMapping
    public ResponseEntity<AttachmentFileDTO> get(@RequestParam Integer id){
        //todo modifiable/accessible only by user owner
        return ResponseEntity.status(HttpStatus.OK).body(attachmentFileService.getUserFile(id));
    }

    @RolesAllowed({"USER"})
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
//todo modifiable/accessible only by user owner
        AttachmentFileDTO attachmentFileDTO = get(id).getBody();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentFileDTO.getName() + "\"")
                .body(attachmentFileDTO.getData());
    }

    @RolesAllowed({"USER"})
    @GetMapping("/list")
    public ResponseEntity<List<AttachmentFileResponse>> getListFiles(){
        //todo modifiable/accessible only by user owner
        List<AttachmentFileResponse> files = attachmentFileService.attachmentUsersFileDTOS().stream().map(
                attachmentFileDTO -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/v1/attachment_file/file/")
                            .path(String.valueOf(attachmentFileDTO.getId()))
                            .toUriString();
                    return new AttachmentFileResponse(
                            attachmentFileDTO.getId(),
                            attachmentFileDTO.getName(),
                            attachmentFileDTO.getType(),
                            fileDownloadUri,
                            attachmentFileDTO.getFileRole()
                    );
                }
        ).collect(Collectors.toList());
//todo modifi for simple view
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @RolesAllowed({"USER"})
    @PutMapping
    public ResponseEntity<AttachmentFileDTO> update(@RequestParam("file")MultipartFile file,
                                                  @RequestParam Integer id )
            throws IOException {
        AttachmentFileDTO attachmentFileDTO = get(id).getBody();
        if(file.getBytes().length !=0){
            attachmentFileDTO.setData(file.getBytes());
            attachmentFileDTO.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            attachmentFileDTO.setType(file.getContentType());
        } else {
            attachmentFileDTO.setData(attachmentFileService.getUserFile(attachmentFileDTO.getId()).getData());
        }
        return ResponseEntity.status(HttpStatus.OK).body(attachmentFileService.updateUsersFile(attachmentFileDTO));
    }
    @RolesAllowed({"USER"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){

        attachmentFileService.deleteUsersFile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"USER"})
    @GetMapping("/roles")
    public ResponseEntity<List<FileRole>> roleList(){
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.stream(FileRole.values()).toList());
    }

    //todo modifiable/accessible only by user owner
    //todo block delete after  accepted value true
}
