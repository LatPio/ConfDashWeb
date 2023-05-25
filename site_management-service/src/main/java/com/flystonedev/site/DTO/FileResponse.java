package com.flystonedev.site.DTO;

public record FileResponse(
        Integer id,
        String name,
        String type,
        String url
) {
}
