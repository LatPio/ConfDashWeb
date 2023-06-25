package com.flystonedev.abstracts;

import com.flystonedev.abstracts.DTO.*;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.model.AttachmentFile;
import com.flystonedev.abstracts.model.FileRole;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SampleData {

    default List<AbstractsEntity> getSampleAbstract(){
        AbstractsEntity abstract1= AbstractsEntity.builder()
                .id(1)
                .abstractTitle("Title: Sample Abstract")
                .body("Body of abstract")
                .authors("Barack Obama")
                .affiliation("Washington DC")
                .presenterId(2)
                .ownerId(2)
                .authId("vava-dddd")
                .accepted(false)
                .files(new ArrayList<>())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        AbstractsEntity abstract2= AbstractsEntity.builder()
                .id(2)
                .abstractTitle("Title 2")
                .body("Body of abstract 2")
                .authors("Bush Junior")
                .affiliation("Capitol")
                .presenterId(2)
                .ownerId(2)
                .authId("vava-dddd")
                .accepted(true)
                .files(new ArrayList<>())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        AbstractsEntity abstract3= AbstractsEntity.builder()
                .id(3)
                .abstractTitle("Title 2")
                .body("Body of abstract 2")
                .authors("Bush Junior")
                .affiliation("Capitol")
                .presenterId(3)
                .ownerId(3)
                .authId("vava-dddd")
                .accepted(true)
                .files(new ArrayList<>())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        return Arrays.asList(abstract1,abstract2, abstract3
        );
    }
    default AbstractDTO getSampleOfOneAbstractDTO(){
        AbstractDTO abstractsEntity= AbstractDTO.builder()
                .id(1)
                .abstractTitle("Title: Sample Abstract")
                .body("Body of abstract")
                .authors("Barack Obama")
                .affiliation("Washington DC")
                .presenterId(2)
                .ownerId(2)
                .authId("vava-dddd")
                .accepted(false)

                .build();
        return abstractsEntity;
    }
    default AbstractsEntity getSampleOfOneAbstractEntity(){
        AbstractsEntity abstractEntity= AbstractsEntity.builder()
                .id(1)
                .abstractTitle("Title: Sample Abstract")
                .body("Body of abstract")
                .authors("Barack Obama")
                .affiliation("Washington DC")
                .presenterId(2)
                .ownerId(2)
                .authId("vava-dddd")
                .accepted(false)
                .files(null)
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        return abstractEntity;
    }

    default AbstractBlockDTO getSampleOfOneAbstractBlockDTO() {
        AbstractBlockDTO abstractBlockDTO = AbstractBlockDTO.builder()
                .id(1)
                .accepted(true)
                .build();
        return abstractBlockDTO;
    }

    default AbstractOutResponse getSampleOfOneAbstractOutResponse(){
        AbstractOutResponse abstractOutResponse = AbstractOutResponse.builder()
                .id(1)
                .abstractTitle("Title: Sample Abstract")
                .body("Body of abstract")
                .presenterId(2)
                .build();
        return abstractOutResponse;
    }


    default List<AttachmentFile> getSampleOfFiles(){
        AttachmentFile attachmentFile1= AttachmentFile.builder()
                .id(1)
                .name("Figure 1")
                .type("multipart/form-data")
                .authId("aaaa-bbbb")
                .accepted(false)
                .fileRole(FileRole.FIGURE)
                .data("1234567".getBytes())
                .abstractsEntity(null)
                .build();

        AttachmentFile attachmentFile2= AttachmentFile.builder()
                .id(2)
                .name("Graphical Abstract")
                .type("multipart/form-data")
                .authId("vava-dddd")
                .accepted(true)
                .fileRole(FileRole.GRAPHICAL_ABSTRACT)
                .data("1234567".getBytes())
                .abstractsEntity(null)
                .build();

        return Arrays.asList(attachmentFile1, attachmentFile2);

    }

    default AttachmentFile getSampleOfOneAttachmentFile(){
        AttachmentFile attachmentFile1= AttachmentFile.builder()
                .id(1)
                .name("Hello.txt")
                .type("text/plain")
                .authId("vava-dddd")
                .accepted(false)
                .fileRole(FileRole.GRAPHICAL_ABSTRACT)
                .data("Hello, World!".getBytes())
                .abstractsEntity(getSampleOfOneAbstractEntity())
                .build();
        return attachmentFile1;
    }
    default AttachmentFileAdminUpdateRequest getSampleOfAttachmentFileAdminUpdateRequest(){
        AttachmentFileAdminUpdateRequest attachmentFile1 =
                new AttachmentFileAdminUpdateRequest(1, FileRole.GRAPHICAL_ABSTRACT, getSampleOfOneAbstractDTO(),false, "vava-dddd" );
        return attachmentFile1;
    }

    default AttachmentFileUserUpdateRequest getSampleOfAttachmentFileUserUpdateRequest(){
        AttachmentFileUserUpdateRequest attachmentFile1 =
                new AttachmentFileUserUpdateRequest(1, FileRole.GRAPHICAL_ABSTRACT, getSampleOfOneAbstractDTO());
        return attachmentFile1;
    }


    default AttachmentFileDTO getSampleOfOneAttachmentFileDTO(){
        AttachmentFileDTO attachmentFileDto= AttachmentFileDTO.builder()
                .id(1)
                .name("Hello.txt")
                .type("text/plain")
                .authId("vava-dddd")
                .accepted(false)
                .fileRole(FileRole.GRAPHICAL_ABSTRACT)
                .data("Hello, World!".getBytes())
//                .abstractsEntity(getSampleOfOneAbstractEntity())
                .build();
        return attachmentFileDto;
    }
    default AttachmentFileRequest getSampleOfOneAttachmentFileRequest(){
        return  new AttachmentFileRequest(FileRole.GRAPHICAL_ABSTRACT, AbstractDTO.builder().id(1).build());
    }
    default AttachmentFileAdminRequest getSampleOfOneAttachmentFileAdminRequest(){
        return  new AttachmentFileAdminRequest(FileRole.GRAPHICAL_ABSTRACT, AbstractDTO.builder().id(1).build(), false,"vava-dddd"  );
    }


    default MultipartFile getSampleMultipart(){
        return new MockMultipartFile(
                "file",
                "Hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
    }




    default List<AbstractsEntity> getCombinatorDataAbstractAndFile(){



        AttachmentFile attachmentFile1= AttachmentFile.builder()
                .id(1)
                .name("Figure 1t")
                .type("????")
                .authId("vava-dddd")
                .accepted(true)
                .fileRole(FileRole.FIGURE)
                .data("1234567".getBytes())
                .abstractsEntity(AbstractsEntity.builder().id(2).build())
                .build();

        AttachmentFile attachmentFile2= AttachmentFile.builder()
                .id(2)
                .name("Graphical Abstract")
                .type("????")
                .authId("vava-dddd")
                .accepted(true)
                .fileRole(FileRole.GRAPHICAL_ABSTRACT)
                .data("1234567".getBytes())
                .abstractsEntity(AbstractsEntity.builder().id(2).build())
                .build();

        AbstractsEntity abstract2= AbstractsEntity.builder()
                .id(2)
                .abstractTitle("Title 2")
                .body("Body of abstract 2")
                .authors("Bush Junior")
                .affiliation("Capitol")
                .presenterId(2)
                .ownerId(2)
                .authId("vava-dddd")
                .accepted(true)
                .files(Arrays.asList(attachmentFile1,attachmentFile2))
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        return Arrays.asList();
    }

}
