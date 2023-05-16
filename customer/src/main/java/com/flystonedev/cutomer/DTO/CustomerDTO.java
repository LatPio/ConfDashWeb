package com.flystonedev.cutomer.DTO;


import com.flystonedev.cutomer.model.InformationLinks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String degree;
    private String phoneNumber;
    private List<InformationLinks> links;
    private DepartmentDTO department;
    private  byte[] photo;
}
