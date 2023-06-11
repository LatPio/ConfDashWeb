package com.flystonedev.cutomer.DTO;

public record InformationLinksAdminRequest(
        String name,
        String urlLink,
        CustomerDTO customer,
        String authId
) {
}
