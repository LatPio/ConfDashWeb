package com.flystonedev.cutomer.DTO;


import com.flystonedev.cutomer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InformationLinksDTO {
    private Integer id;
    private String name;
    private String urlLink;
    private CustomerDTO customer;
}
