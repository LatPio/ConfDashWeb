package com.flystonedev.customer.repository;

import com.flystonedev.customer.SampleData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerRepositoryTest implements SampleData {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        customerRepository.saveAll(getSampleOfCustomers());
    }
    @AfterEach
    void tearDown(){
        customerRepository.deleteAll();
    }

    @Test
    void findCustomerByIdAndAuthID() {

        String authId ="bbbb";

        Integer id = 1;


        var testValue = customerRepository.findCustomerByIdAndAuthID(id, authId);
        assertThat(testValue.get().getId(), is(id));

    }
}