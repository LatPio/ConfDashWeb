package com.flystonedev.cutomer.records;

import com.flystonedev.cutomer.model.Institution;

public record DepartmentResponse(
        Integer id,
        String name,
        String street,
        String buildingNumber,
        String flatNumber,
        String city,
        String  postalCode,
        String country,
        Institution institution
) {
}
