package com.flystonedev.customer.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDataDTO {
    private Integer id;
    private String name;
    private String street;
    private String buildingNumber;
    private String flatNumber;
    private String city;
    private String postalCode;
    private String country;
    private String taxIdentificationNumber;
    private String institutionShortName;
    private String institution;
}
