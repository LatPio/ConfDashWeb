package com.flystonedev.customer.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerAdminDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String degree;
    private String phoneNumber;
    private String authID;
    private List<InformationLinksDTO> links;
    private DepartmentDTO department;
    private ProfilePhotoDTO photo;
}
