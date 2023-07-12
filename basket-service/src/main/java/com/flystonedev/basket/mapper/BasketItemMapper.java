package com.flystonedev.basket.mapper;

import com.flystonedev.basket.DTO.BasketDTO;
import com.flystonedev.basket.model.BasketItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketItemMapper {

    BasketItem map(BasketDTO basketDTO);

    BasketDTO map(BasketItem basketItem);
}
