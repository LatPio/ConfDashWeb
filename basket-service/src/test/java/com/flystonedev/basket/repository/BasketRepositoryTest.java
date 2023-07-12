package com.flystonedev.basket.repository;

import com.flystonedev.basket.SampleData;
import com.flystonedev.basket.model.BasketItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DataJpaTest
class BasketRepositoryTest implements SampleData {

    @Autowired
    private BasketRepository basketRepository;

    @BeforeEach
    void setUp(){
        var data = getSampleBasketItems();
        basketRepository.saveAll(data);
    }

    @AfterEach
    void tearDown(){
        basketRepository.deleteAll();
    }
    @Test
    void findBasketItemByAuthId() {
        var data = getSampleBasketItems().get(0);
        var testValue = basketRepository.findBasketItemByAuthId(data.getAuthId());

        assertThat(testValue.get(0).getAuthId(), is(data.getAuthId()));
    }

    @Test
    void findBasketItemByIdAndAuthId() {
        var data = getSampleBasketItems().get(0);
        var testValue = basketRepository.findBasketItemByIdAndAuthId(data.getId(), data.getAuthId());

        assertThat(testValue.get().getId(), is(data.getId()));
        assertThat(testValue.get().getAuthId(), is(data.getAuthId()));
    }

    @Test
    void deleteBasketItemByIdAndAuthId() {
        var data = getSampleBasketItems().get(0);
        basketRepository.deleteBasketItemByIdAndAuthId(data.getId(), data.getAuthId());
        Optional<BasketItem> shouldBeEmpty = basketRepository.findBasketItemByIdAndAuthId(data.getId(), data.getAuthId());
        assertThat(shouldBeEmpty, is(Optional.empty()));

    }
}