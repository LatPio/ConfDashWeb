package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.model.AttachmentFile;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttachmentFileRepositoryTest implements SampleData {

    @Autowired
    private AttachmentFileRepository underTest;
    @Autowired
    private  AbstractRepository underTestParent;

    @BeforeEach
    void setUp(){
        var parentData = getSampleAbstract();
        var data = getSampleOfFiles();
        underTestParent.saveAll(parentData);
        underTest.saveAll(data);
    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
        underTestParent.deleteAll();
    }


    @Test
    @Order(1)
    void findAttachmentFileByAuthId() {
        //given
        String authId = "aaaa-bbbb";
        //when
        List<AttachmentFile> attachmentFileByAuthId = underTest.findAttachmentFileByAuthId(authId);
        //then
        assertThat(attachmentFileByAuthId.stream().findFirst().get().getAuthId(), is(authId));

    }

    @Test
    @Order(2)
    void findAttachmentFileByIdAndAuthId() {
        //given
        Integer id = 3;
        String authId = "aaaa-bbbb";
        //when
        Optional<AttachmentFile> fileByIdAndAuthId = underTest.findAttachmentFileByIdAndAuthId(id, authId);
        //then
        assertThat(fileByIdAndAuthId.get().getAuthId(), is(authId));
    }

    @Test
    @Order(3)
    void deleteAttachmentFileByIdAndAuthId() {
        //given
        Integer id = 1;
        String authId = "aaaa-bbbb";
        //when
        underTest.deleteAttachmentFileByIdAndAuthId(id,authId);
        Optional<AttachmentFile> fileByIdAndAuthId = underTest.findAttachmentFileByIdAndAuthId(id, authId);
        //then
        assertThat(fileByIdAndAuthId, is(Optional.empty()));
    }

    @Test
    @Order(4)
    void deleteById() {
        //given
        Integer id = 1;
       //when
        underTest.deleteById(id);
        Optional<AttachmentFile> fileByIdAndAuthId = underTest.findById(id);
        //then
        assertThat(fileByIdAndAuthId, is(Optional.empty()));
    }
//    @Test
//    @Order(5)
//    void blockAttachmentFileForEdition(){
//        //given
//        Integer idOfAbstract= 1;
//        //when
//        List<AttachmentFile> attachmentFileByAbstractsEntityId = underTest.findAttachmentFileByAbstractsEntity_Id(idOfAbstract);
//        //then
//        assertThat(attachmentFileByAbstractsEntityId.stream().findFirst().get().getAbstractsEntity().getId(), is(idOfAbstract));
//    }
}