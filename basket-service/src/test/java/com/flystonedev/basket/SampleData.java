package com.flystonedev.basket;

import com.flystonedev.basket.DTO.BasketDTO;
import com.flystonedev.basket.model.BasketItem;

import java.util.Arrays;
import java.util.List;

public interface SampleData {

    default List<BasketItem> getSampleBasketItems(){
        BasketItem item1 = BasketItem.builder()
                .id(1)
                .name("Name of Item In Basket")
                .eventId(1)
                .deletable(true)
                .authId("aaaa-bbbb")
                .build();
        BasketItem item2 = BasketItem.builder()
                .id(2)
                .name("Name of Second Item In Basket")
                .eventId(2)
                .deletable(true)
                .authId("aaaa-bbbb")
                .build();
        return Arrays.asList(item1,item2);
    }

    default BasketDTO getSampleOfBasketDTO(){
        BasketDTO item1 = BasketDTO.builder()
                .id(1)
                .name("Name of Item In Basket")
                .eventId(1)
                .deletable(true)
                .authId("aaaa-bbbb")
                .build();
        return item1;
    }
    default BasketItem getSampleOfBasketItem(){
        BasketItem item1 = BasketItem.builder()
                .id(1)
                .name("Name of Item In Basket")
                .eventId(1)
                .deletable(true)
                .authId("aaaa-bbbb")
                .build();
        return item1;
    }
}
