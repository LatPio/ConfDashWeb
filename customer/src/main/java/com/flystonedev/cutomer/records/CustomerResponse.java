package com.flystonedev.cutomer.records;


import com.flystonedev.cutomer.model.Department;
import com.flystonedev.cutomer.model.InformationLinks;

import java.util.List;

public record CustomerResponse(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String degree,
        String phoneNumber,
        List<InformationLinks> links,
        Department department,

        byte[] photo

) {
}
