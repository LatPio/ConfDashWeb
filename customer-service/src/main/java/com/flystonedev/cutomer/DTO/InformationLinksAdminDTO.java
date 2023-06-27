package com.flystonedev.cutomer.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InformationLinksAdminDTO {
    private Integer id;
    private String name;
    private String urlLink;
    private String authId;
//    private CustomerDTO customer;
}
