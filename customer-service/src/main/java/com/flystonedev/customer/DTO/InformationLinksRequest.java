package com.flystonedev.customer.DTO;

public record InformationLinksRequest(
        String name,
        String urlLink,
        CustomerDTO customer
) {
}
