package com.flystonedev.customer.repository;

import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.model.ProfilePhoto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProfilePhotoRepositoryTest implements SampleData {

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @BeforeEach
    void setUp(){
        customerRepository.saveAll(getSampleOfCustomers());
        profilePhotoRepository.saveAll(getSampleOfProfilePhotos());
    }
    @AfterEach
    void tearDown(){
        customerRepository.deleteAll();
        profilePhotoRepository.deleteAll();

    }

    @Test
    @Order(2)
    void deleteById() {
        //given
        Integer id = 1;
        //when
        profilePhotoRepository.deleteById(id);
        Optional<ProfilePhoto> byId = profilePhotoRepository.findById(id);
        //then
        assertThat(byId, is(Optional.empty()));
    }

    @Test
    @Order(1)
    void findProfilePhotoByIdAndAndId() {
        //given
        Integer id = 1;
        String authId = "aaaa";
        String testName = "photo1";
        //when
        Optional<ProfilePhoto> profilePhotoByIdAnd = profilePhotoRepository.findProfilePhotoByIdAndAuthId(id, authId);
        //then
        assertThat(profilePhotoByIdAnd.get().getName(), is(testName));
    }
}