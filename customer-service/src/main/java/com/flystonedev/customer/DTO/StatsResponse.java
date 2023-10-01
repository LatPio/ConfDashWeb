package com.flystonedev.customer.DTO;

public record StatsResponse(
        Long numberOfUsers,
        Long numberOfDepartments,
        Long numberOfInstitutions,
        Long numberOfDifferentCountry
) {
}
