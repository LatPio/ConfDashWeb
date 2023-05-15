package com.flystonedev.cutomer.records;

import java.util.List;

public record InstitutionRecord(
        Integer id,
        String name,

        List<DepartmentResponse> departmentResponseList

) {
}
