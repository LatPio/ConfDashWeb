package com.flystonedev.customer.DTO;

public record ProfilePhotoResponse(
        Integer id,
        String name,
        String type,
        String url
) {
}
