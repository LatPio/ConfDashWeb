package com.flystonedev.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProfilePhoto")
public class ProfilePhoto {
    @Id
    private Integer id;
    private String name;
    private String type;
    private String authId;
    @Lob
    private byte[] data;

    @OneToOne
    @MapsId
    @JoinColumn
    private Customer customer;


}
