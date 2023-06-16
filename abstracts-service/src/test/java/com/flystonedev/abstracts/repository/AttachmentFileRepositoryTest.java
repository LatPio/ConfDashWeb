package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.model.AttachmentFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@DataJpaTest
class AttachmentFileRepositoryTest implements SampleData {

    @Autowired
    private AttachmentFileRepository underTest;
    @Autowired
    private  AbstractRepository underTestParent;

    @BeforeEach
    void setUp(){
        var parentData = getCombinatorDataAbstractAndFile();
        var data = getSampleOfFiles();
        underTestParent.saveAll(parentData);
        underTest.saveAll(data);
    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }


    @Test
    void findAttachmentFileByAuthId() {
        //given
        Integer id = 1;
        String authId = "aaaa-bbbb";
        //when
        List<AttachmentFile> attachmentFileByAuthId = underTest.findAttachmentFileByAuthId(authId);
        //then
        assertThat(attachmentFileByAuthId.stream().findFirst().get().getAuthId(), is(authId));

    }

    @Test
    void findAttachmentFileByIdAndAuthId() {
        //given
        Integer id =1;
        String authId = "aaaa-bbbb";
        //when
        Optional<AttachmentFile> fileByIdAndAuthId = underTest.findAttachmentFileByIdAndAuthId(id, authId);
        //then
        assertThat(fileByIdAndAuthId.get().getAuthId(), is(authId));
    }

    @Test
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
    void deleteById() {
        //given
        Integer id = 1;
       //when
        underTest.deleteById(id);
        Optional<AttachmentFile> fileByIdAndAuthId = underTest.findById(id);
        //then
        assertThat(fileByIdAndAuthId, is(Optional.empty()));
    }
}