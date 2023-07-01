package com.flystonedev.customer.repository;

import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.model.InformationLinks;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InformationLinksRepositoryTest implements SampleData {

    @Autowired
    private InformationLinksRepository informationLinksRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @BeforeEach
    void setUp(){
        customerRepository.saveAll(getSampleOfCustomers());
        informationLinksRepository.saveAll(getSampleOfInformationLinks());
    }
    @AfterEach
    void tearDown(){
        informationLinksRepository.deleteAll();

    }

    @Test
    @Order(1)
    void findInformationLinksByIdAndAuthId() {

        String authId ="aaaa";

        Integer id = 1;

        var testValue = informationLinksRepository.findInformationLinksByIdAndAuthId(id, authId);
        assertThat(testValue.get().getId(), is(id));
        assertThat(testValue.get().getName(), is("LinkedIn"));
    }

    @Test
    @Order(2)
    void findInformationLinksByAuthId() {
        String authId ="aaaa";

        List<InformationLinks> informationLinksByAuthId = informationLinksRepository.findInformationLinksByAuthId(authId);
        assertThat(informationLinksByAuthId.stream().findFirst().get().getAuthId(), is(authId));
    }
}