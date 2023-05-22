package com.flystonedev.cutomer.DTO;

import com.flystonedev.cutomer.model.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
    private CustomerDTO customer;
}
