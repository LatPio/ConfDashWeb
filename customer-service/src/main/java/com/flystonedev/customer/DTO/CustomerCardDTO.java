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
public class CustomerCardDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String degree;
    private List<InformationLinksDTO> links;
    private DepartmentDTO department;
    private ProfilePhotoDTO photo;
}
