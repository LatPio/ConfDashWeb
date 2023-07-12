package com.flystonedev.basket.repository;

import com.flystonedev.basket.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<BasketItem, Integer> {

    List<BasketItem> findBasketItemByAuthId(String authId);

    Optional<BasketItem> findBasketItemByIdAndAuthId(Integer id , String authId);

    void deleteBasketItemByIdAndAuthId(Integer id , String authID);
}
