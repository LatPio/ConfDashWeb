package com.flystonedev.cutomer.records;

import com.flystonedev.cutomer.model.Customer;

public record InformationLinksResponse(
        Integer id,
        String name,
        String urlLink,
        Customer customer
) {
}
