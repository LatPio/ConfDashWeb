package com.flystonedev.abstracts.controller;

import com.flystonedev.abstracts.DTO.AttachmentFileAdminRequest;
import com.flystonedev.abstracts.DTO.AttachmentFileAdminUpdateRequest;
import com.flystonedev.abstracts.DTO.AttachmentFileDTO;
import com.flystonedev.abstracts.DTO.AttachmentFileResponse;
import com.flystonedev.abstracts.model.FileRole;
import com.flystonedev.abstracts.service.AttachmentFileService;
import com.flystonedev.abstracts.tools.ResizeImage;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/admin/attachment_file")
@AllArgsConstructor
public class AttachmentFileAdminController {

    private final AttachmentFileService attachmentFileService;


    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<AttachmentFileDTO> saveFile(
            @RequestPart("file")MultipartFile file,
            @RequestPart("data") AttachmentFileAdminRequest attachmentFileAdminRequest){
        try{
            log.info("File saved!");
            return ResponseEntity.status(HttpStatus.OK).body(attachmentFileService.saveAdminFile(file,attachmentFileAdminRequest));
        } catch (IOException e) {
            log.error("Error occurred while saving to database! ");
            throw new RuntimeException(e);
        }
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<AttachmentFileDTO> get(@RequestParam Integer id){
        //todo modifiable/accessible only by user owner
        return ResponseEntity.status(HttpStatus.OK).body(attachmentFileService.getAdminFile(id));
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        AttachmentFileDTO attachmentFileDTO = get(id).getBody();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentFileDTO.getName() + "\"")
                .body(attachmentFileDTO.getData());
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/list")
    public ResponseEntity<List<AttachmentFileResponse>> getListFiles(){
        List<AttachmentFileResponse> files = attachmentFileService.attachmentAdminFileDTOS().stream().map(
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

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<AttachmentFileDTO> update(
            @RequestPart(value = "file",required = false)MultipartFile file,
            @RequestPart("data") AttachmentFileAdminUpdateRequest attachmentFileAdminUpdateRequest) {

        try{
            log.info("File updated!");
            return ResponseEntity.status(HttpStatus.OK).body(attachmentFileService.updateAdmin(file, attachmentFileAdminUpdateRequest));
        } catch (IOException e) {
            log.error("Error occurred while saving to database! ");
            throw new RuntimeException(e);
        }
    }
    @RolesAllowed({"ADMIN"})
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){

        attachmentFileService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/roles")
    public ResponseEntity<List<FileRole>> roleList(){
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.stream(FileRole.values()).toList());
    }

    //todo block endpoint
}
