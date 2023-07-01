package com.flystonedev.customer.repository;

import com.flystonedev.customer.SampleData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DataJpaTest
class DepartmentRepositoryTest implements SampleData {

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp(){
        departmentRepository.saveAll(getSampleOfDepartment());
    }
    @AfterEach
    void tearDown(){
        departmentRepository.deleteAll();
    }

    @Test
    void findDepartmentByName() {


        String testName = "Test Department";
        Integer id =  1;
        var testValue = departmentRepository.findDepartmentByName(testName);

        assertThat(testValue.getId(), is(id));


    }
}