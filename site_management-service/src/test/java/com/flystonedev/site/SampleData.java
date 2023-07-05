package com.flystonedev.site;

import com.flystonedev.site.DTO.FilesDTO;
import com.flystonedev.site.DTO.SiteDTO;
import com.flystonedev.site.model.Files;
import com.flystonedev.site.model.Site;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public interface SampleData {

    default SiteDTO getSampleOfSiteDTO(){
        SiteDTO siteDTO = SiteDTO.builder()
                .id(1)
                .name("Name")
                .body("Body")
                .orderNumber(1)
                .visible(true)
                .build();
        return siteDTO;
    }

    default MultipartFile getSampleMultipart(){
        return new MockMultipartFile(
                "file",
                "Hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
    }

    default FilesDTO getSampleOfFilesDTO(){
        FilesDTO filesDTO = FilesDTO.builder()
                .id(1)
                .name("Hello.txt")
                .type("text/plain")
                .data("Hello, World!".getBytes())
                .build();
        return filesDTO;
    }
    default Files getSampleOfFiles(){
        Files files = Files.builder()
                .id(1)
                .name("Hello.txt")
                .type("text/plain")
                .data("Hello, World!".getBytes())
                .build();
        return files;
    }
}
