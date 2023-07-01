package com.flystonedev.customer.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfilePhotoDTO {

    private Integer id;
    private String name;
    private String type;
    private byte[] data;
    private String authId;
    private CustomerIdDTO customer;

}
