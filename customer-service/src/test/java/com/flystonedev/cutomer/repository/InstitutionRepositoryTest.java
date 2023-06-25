package com.flystonedev.cutomer.repository;

import com.flystonedev.cutomer.SampleData;
import com.flystonedev.cutomer.model.InformationLinks;
import com.flystonedev.cutomer.model.Institution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
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