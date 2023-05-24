package com.flystonedev.cutomer.DTO;

import com.flystonedev.cutomer.model.Customer;

public record InformationLinksRequest(
        String name,
        String urlLink,
        CustomerDTO customer
) {
}
