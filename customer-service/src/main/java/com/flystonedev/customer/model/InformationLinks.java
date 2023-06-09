package com.flystonedev.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InformationLinks {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String urlLink;
    private String authId;
    @ManyToOne()
    @JoinColumn(name = "customer")
    private Customer customer;
}
