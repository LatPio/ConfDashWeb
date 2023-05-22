package com.flystonedev.cutomer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProfilePhoto")
public class ProfilePhoto {
    @Id
    @Column(name = "customer_id")
    private Integer id;
    private String name;
    private String type;
    @Lob
    private byte[] data;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
