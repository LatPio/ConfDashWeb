package com.flystonedev.basket.DTO;

import java.util.List;

public record BasketManyRequest(
        String name,
        Integer eventId,
        Boolean deletable,
        List<String> authIds
) {
}
