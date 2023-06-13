package com.flystonedev.abstracts.repository;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.model.AbstractsEntity;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;


import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


@DataJpaTest
class AbstractRepositoryTest implements SampleData{

    @Autowired
    private  AbstractRepository underTest;

    @BeforeEach
    void setUp(){
        var data = getSampleAbstract();
        underTest.saveAll(data);
    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void shouldCheckIfAbstractPresentByIdAndAuthId() {
        Integer id = 1;
        String authId = "aaaa-bbbb";
        //when
        Optional<AbstractsEntity> byIdAndAuthId;
        byIdAndAuthId = underTest.findByIdAndAuthId(id, authId);
        //then
        System.out.println(byIdAndAuthId);
    }


    @Test
    void idShouldReturnAbstractsEntitiesByAuthId() {
        //given
        String authId = "aaaa-bbbb";
        //when
        var byAuthId = underTest.findAbstractsEntitiesByAuthId(authId);
        //then
        assertThat(byAuthId.stream().findFirst().get().getAuthId(), is(authId));
        assertThat(byAuthId, not(Optional.empty()));

    }



    @Test
    void findAbstractsEntitiesByAccepted() {
        //given
        Boolean test = true;
        //when
        List<AbstractsEntity> abstractsEntitiesByAccepted = underTest.findAbstractsEntitiesByAccepted(test);
        //then
        assertThat(abstractsEntitiesByAccepted.stream().findFirst().get().isAccepted(), is(test));
        assertThat(abstractsEntitiesByAccepted, not(Optional.empty()));


    }
    @Test
    void deleteByIdAndAuthId() {
        //given
        Integer id = 2;
        String authID = "vava-dddd";
        //when
        underTest.deleteByIdAndAuthId(id, authID);
        Optional<AbstractsEntity> shouldByEmpty = underTest.findByIdAndAuthId(id, authID);

        //then

        assertThat(shouldByEmpty, is(Optional.empty()));
    }
}