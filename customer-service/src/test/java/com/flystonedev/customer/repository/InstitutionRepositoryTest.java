package com.flystonedev.customer.repository;

import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.model.Institution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DataJpaTest
class InstitutionRepositoryTest implements SampleData {

    @Autowired
    private InstitutionRepository institutionRepository;

    @BeforeEach
    void setUp() {
        institutionRepository.saveAll(getSampleOfInstitution());

    }

    @AfterEach
    void tearDown() {
        institutionRepository.deleteAll();
    }

    @Test
    void findInstitutionByName() {
        String testName ="University";

        Institution institutionByName = institutionRepository.findInstitutionByName(testName);
        assertThat(institutionByName.getName(), is(testName));
    }
}