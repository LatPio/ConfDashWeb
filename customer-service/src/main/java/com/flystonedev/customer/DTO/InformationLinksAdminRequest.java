package com.flystonedev.customer.DTO;

public record InformationLinksAdminRequest(
        String name,
        String urlLink,
        CustomerDTO customer,
        String authId
) {
}
